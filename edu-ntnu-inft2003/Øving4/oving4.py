# Big Data - Øving 4, Høst 2024
# Student: Jens Chrstian Aanestad

# Imports
import math
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
from sklearn.metrics import r2_score, mean_squared_error, mean_absolute_error, confusion_matrix, accuracy_score, classification_report
from sklearn.preprocessing import StandardScaler
from sklearn.neighbors import KNeighborsClassifier

# Funksjoner for rensing av data og mer...

# Removes zeros
def clean_remove_zeros(dataframe):
    dataframe.columns = dataframe.columns.str.strip()
    dataframe = dataframe[(dataframe != 0).all(axis=1)]
    return dataframe


# Removes NaN
def clean_remove_nan(dataframe):
    dataframe.columns = dataframe.columns.str.strip()
    dataframe = dataframe.dropna()
    return dataframe


# Replaces NaN with total mean
def clean_replace_nan_with_mean(dataframe):
    dataframe.columns = dataframe.columns.str.strip()
    for i in dataframe.columns:
        if dataframe[i].dtype in ['float64', 'int64']:
            mean_value = dataframe[i].mean(skipna=True)
            dataframe[i] = dataframe[i].fillna(mean_value)
    return dataframe


# Replaces NaN with total median
def clean_replace_nan_with_median(dataframe):
    dataframe.columns = dataframe.columns.str.strip()
    for i in dataframe.columns:
        if dataframe[i].dtype in ['float64', 'int64']:
            median_value = dataframe[i].median(skipna=True)
            dataframe[i] = dataframe[i].fillna(median_value)
    return dataframe


# Specially adapted for the life-expectancy-data set
def clean_replace_nan_with_grouped_median(dataframe):
    dataframe.columns = dataframe.columns.str.strip()
    groups = dataframe.groupby('Country')
    for country, group in groups:
        for column in group.columns:
            if group[column].dtype in ['float64', 'int64']:
                median_value = dataframe[column].median(skipna=True)
                if group[column].notna().sum() > 0:
                    median_value = group[column].median(skipna=True)
                dataframe.loc[dataframe['Country'] == country, column] = group[column].fillna(median_value)
    return dataframe


# Specially adapted for the H1 dataset
def clean_replace_zero_adr_with_grouped_mean(dataframe):
    dataframe.columns = dataframe.columns.str.strip()
    groups = dataframe.groupby('Country')
    for country, group in groups:
        group['ADR'] = group['ADR'].apply(lambda x: np.nan if x <= 0 else x)
        mean_value = group['ADR'].mean(skipna=True)
        if pd.isna(mean_value):
            mean_value = dataframe['ADR'].mean(skipna=True)
        filled_values = group['ADR'].fillna(mean_value)
        if dataframe['ADR'].dtype == 'int64':
            filled_values = filled_values.astype(int)
        dataframe.loc[dataframe['Country'] == country, 'ADR'] = filled_values
    return dataframe


# Specially adapted for the H1 dataset
def clean_replace_nan_with_grouped_mean(dataframe, listOfFeatures):
    dataframe.columns = dataframe.columns.str.strip()
    groups = dataframe.groupby('Country')
    for country, group in groups:
        for feature in listOfFeatures:
            mean_value = group[feature].mean(skipna=True)
            if pd.isna(mean_value):
                mean_value = dataframe[feature].mean(skipna=True)
            filled_values = group[feature].fillna(mean_value)
            if dataframe[feature].dtype == 'int64':
                filled_values = filled_values.astype(int)
            dataframe.loc[dataframe['Country'] == country, feature] = filled_values
    return dataframe


# Specially adapted for the ov4-breast-cancer dataset
def clean_replace_question_mark_with_median(dataframe):
    dataframe.columns = dataframe.columns.str.strip()
    for i in dataframe.columns:
        dataframe[i] = dataframe[i].replace('?', np.nan)
        dataframe[i] = pd.to_numeric(dataframe[i], errors='coerce')
        median_value = dataframe[i].median(skipna=True)
        dataframe[i] = dataframe[i].fillna(median_value)
    return dataframe


# Choose k
def choose_k(dataframe):
    k = int(math.sqrt(dataframe.shape[0]))
    if k % 2 == 0:
        k = k + 1
    return k


# Rensing av life-expectancy-data.csv
"""
Life expectancy - Renser ved å erstatte NaN verdier med median
"""
df_life_expectancy = pd.read_csv('life-expectancy-data.csv')
df_life_expectancy = clean_replace_nan_with_grouped_median(df_life_expectancy.copy())
print(df_life_expectancy)


# Oppgave 1
print("Oppgave 1\n")
# Interesting columns and target (X and y)
columns = ['Alcohol', 'percentage expenditure', 'BMI', 'Schooling', 'GDP']
target = 'Life expectancy'

# Initialize dictionary to store R-squared scores, mean squared error, absolute squared error and intercept
r2_scores = {}
mse_dictionary = {}
mae_dictionary = {}
intercept_dictionary = {}

# Iterate over the selected columns to fit a linear regression model
for column in columns:
    X = df_life_expectancy[[column]]
    y = df_life_expectancy[target]

    # Split the data into training and test sets
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

    # Initialize and train the linear regression model
    model = LinearRegression()
    model.fit(X_train, y_train)

    # Make predictions on the test set
    y_pred = model.predict(X_test)

    # Calculate the R-squared score
    r2 = r2_score(y_test, y_pred)
    r2_scores[column] = r2

    # Calculate the mean square error
    mse = mean_squared_error(y_test, y_pred)
    mse_dictionary[column] = mse

    # Calculate mean absolute error
    mae = mean_absolute_error(y_test, y_pred)
    mae_dictionary[column] = mae

    intercept = model.intercept_
    intercept_dictionary[column] = intercept

# Find the column with the highest R-squared score, mean squared error, and mean absolute error
best_r2_predictor = max(r2_scores, key=r2_scores.get)
best_mse_predictor = min(mse_dictionary, key=mse_dictionary.get)
best_mae_predictor = min(mae_dictionary, key=mae_dictionary.get)

best_r2_score = r2_scores[best_r2_predictor]
best_mse_score = mse_dictionary[best_mse_predictor]
best_mae_score = mae_dictionary[best_mae_predictor]

# Display R-squared scores for all columns
print("R-squared scores:")
for col, score in r2_scores.items():
    print(f"\tR-squared for {col}: {score:.4f}")

# Display mean square error for all columns
print("\n Mean squared errors:")
for col, error in mse_dictionary.items():
    print(f"\tMean squared error for {col}: {error:.4f}")

# Display mean absolute values for all columns
print("\n Mean absolute errors:")
for col, error in mae_dictionary.items():
    print(f"\tMean absolute error for {col}: {error:.4f}")

# Display the results
print(
    f"\nThe best predictor of Life Expectancy based on R2 scores is '{best_r2_predictor}' with an R-squared score of {best_r2_score:.4f}")
print(
    f"The best predictor of LIfe Expectancy based on mean squared error is '{best_mse_predictor}' with an mean square error of {best_mse_score:.4f}' ")
print(
    f"The best predictor of LIfe Expectancy based on mean absolute error is '{best_mae_predictor}' with an mean absolute error of {best_mae_score:.4f}' ")


# Oppgave 2
print("Oppgave 2\n")

# Retrieves a list of all countries subsets
countries = df_life_expectancy['Country'].unique()

# Dictionary to contain the countries' life expectancies in 2020
life_expectancy_2020 = {}

# Iterate over each country and fit a linear regression model
for country in countries:
    country_data = df_life_expectancy[df_life_expectancy['Country'] == country]

    # Ensure we have enough data to build a model
    if len(country_data) < 2:
        continue

    X = country_data[['Year']]
    y = country_data['Life expectancy']

    # Split the data into training and test sets
    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

    # Initialize and train the linear regression model
    model = LinearRegression()
    model.fit(X_train, y_train)

    # Make predictions for the year 2020
    predicted_life_expectancy = model.predict(pd.DataFrame([[2020]], columns=['Year']))[0]

    # Store the prediction for this country
    life_expectancy_2020[country] = predicted_life_expectancy

# Convert the dictionary to a pandas Series
life_expectancy_2020_series = pd.Series(life_expectancy_2020)

# Find the country with the highest predicted life expectancy for 2020
best_country = life_expectancy_2020_series.idxmax()
best_life_expectancy = life_expectancy_2020_series.max()

# Display the result
print(
    f"The country predicted with best life expectancy in 2020 is '{best_country}' with a life expectancy of {best_life_expectancy:.2f} years.")

# Display the top 10 countries with the highest life expectancy
top_10_countries = life_expectancy_2020_series.nlargest(10)
print("\nTop 10 countries with the highest predicted life expectancy in 2020:")
print(top_10_countries)


# Rensing av H1.csv
"""
H1 - Renser ved å erstatte NaN
"""
df_h1 = pd.read_csv('H1.csv')
features = ['ADR', 'BookingChanges', 'PreviousCancellations', 'PreviousBookingsNotCanceled',
            'Adults', 'Children', 'Babies', 'IsRepeatedGuest', 'RequiredCarParkingSpaces', 'TotalOfSpecialRequests']
df_h1 = clean_replace_zero_adr_with_grouped_mean(df_h1)
df_h1 = clean_replace_nan_with_grouped_mean(df_h1, features)

print(df_h1[features])


# Oppgave 3
print("Oppgave 3\n")

# Define the features and target
features = ['ADR', 'BookingChanges', 'PreviousCancellations', 'PreviousBookingsNotCanceled',
            'Adults', 'Children', 'Babies', 'IsRepeatedGuest', 'RequiredCarParkingSpaces', 'TotalOfSpecialRequests']
target = 'IsCanceled'

# Select the features and target from the dataset
X = df_h1[features]
y = df_h1[target]

# Split the data into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Normalize the features using StandardScaler
scaler = StandardScaler()
X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

# Choosing k by square root of y_test
k = choose_k(y_test)

# Initialize and training the KNN classifier
knn = KNeighborsClassifier(k)
knn.fit(X_train, y_train)

# Predict on the test set
y_pred = knn.predict(X_test)

# Print k
print(f"k: {k}")

# Print a detailed classification report
print("\nClassification Report:")
print(classification_report(y_test, y_pred))

# Print the confusion matrix
c_m = confusion_matrix(y_test, y_pred)
print("Confusion Matrix:")
print(c_m)

# True Positive Rate
true_positive_rate = c_m[0][0] / (c_m[0][0] + c_m[1][0])

# True Negative Rate
true_negative_rate = c_m[1][1] / (c_m[1][1] + c_m[0][1])

# Accuracy
accuracy = (c_m[1][1] + c_m[0][0]) / (c_m[1][1] + c_m[1][0] + c_m[0][1] + c_m[0][0])

print(f"\nTrue positive rate: {true_positive_rate:.2f}")
print(f"True negative rate: {true_negative_rate:.2f}")
print(f"Accuracy: {accuracy:.2f}")


# Resning av ov4-breast-cancer.csv
"""
Renser ved å erstatte NaN med median
"""
df_breast_cancer = pd.read_csv('ov4-breast-cancer.csv')
df_breast_cancer = clean_replace_question_mark_with_median(df_breast_cancer)
print(df_breast_cancer)


# Oppgave 4 - Med normalisering
print("Oppgave 4\n")

# Select the features and target from the dataset
X = df_breast_cancer.drop('classes', axis=1)
y = df_breast_cancer['classes']

# Split the data into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Normalize the features using StandardScaler
scaler = StandardScaler()
X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

# Choosing k by square root of y_test
k = choose_k(y_test)

# Initialize and training the KNN classifier
knn = KNeighborsClassifier(k)
knn.fit(X_train, y_train)

# Predict on the test set
y_pred = knn.predict(X_test)

# Print k
print(f"k: {k}")

# Print a detailed classification report
print("\nClassification Report:")
print(classification_report(y_test, y_pred))

# Print the confusion matrix
c_m = confusion_matrix(y_test, y_pred)
print("Confusion Matrix:")
print(c_m)

# True Positive Rate
true_positive_rate = c_m[0][0] / (c_m[0][0] + c_m[1][0])

# True Negative Rate
true_negative_rate = c_m[1][1] / (c_m[1][1] + c_m[0][1])

# Accuracy
accuracy = (c_m[1][1] + c_m[0][0]) / (c_m[1][1] + c_m[1][0] + c_m[0][1] + c_m[0][0])

print(f"\nTrue positive rate: {true_positive_rate:.2f}")
print(f"True negative rate: {true_negative_rate:.2f}")
print(f"Accuracy: {accuracy:.2f}")


# Oppgave 4- Uten normalisering
# Select the features and target from the dataset
X = df_breast_cancer.drop('classes', axis=1)
y = df_breast_cancer['classes']

# Split the data into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Choosing k by square root of y_test
k = choose_k(y_test)

# Initialize and training the KNN classifier
knn = KNeighborsClassifier(k)
knn.fit(X_train, y_train)

# Predict on the test set
y_pred = knn.predict(X_test)

# Print k
print(f"k: {k}")

# Print a detailed classification report
print("\nClassification Report:")
print(classification_report(y_test, y_pred))

# Print the confusion matrix
c_m = confusion_matrix(y_test, y_pred)
print("Confusion Matrix:")
print(c_m)

# True Positive Rate
true_positive_rate = c_m[0][0] / (c_m[0][0] + c_m[1][0])

# True Negative Rate
true_negative_rate = c_m[1][1] / (c_m[1][1] + c_m[0][1])

# Accuracy
accuracy = (c_m[1][1] + c_m[0][0]) / (c_m[1][1] + c_m[1][0] + c_m[0][1] + c_m[0][0])

print(f"\nTrue positive rate: {true_positive_rate:.2f}")
print(f"True negative rate: {true_negative_rate:.2f}")
print(f"Accuracy: {accuracy:.2f}")


# Plotter accuracy for forskjellige K-verdier (med normalisering)
# Select the features and target from the dataset
X = df_breast_cancer.drop('classes', axis=1)
y = df_breast_cancer['classes']

# Split the data into training and test sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Normalize the features using StandardScaler
scaler = StandardScaler()
X_train = scaler.fit_transform(X_train)
X_test = scaler.transform(X_test)

# List to store accuracy values for each k
accuracies = []
# List to store confusion matrices
confusion_matrices = []

# Test for k values from 1 to 20
for k in range(1, 21):
    knn = KNeighborsClassifier(n_neighbors=k)
    knn.fit(X_train, y_train)
    y_pred = knn.predict(X_test)
    accuracy = accuracy_score(y_test, y_pred)
    accuracies.append(accuracy)
    c_m = confusion_matrix(y_test, y_pred)
    confusion_matrices.append(c_m)

# Plot the accuracies vs. k values
plt.figure(figsize=(10, 6))
plt.plot(range(1, 21), accuracies, marker='o', linestyle='-', color='#ff0066')
plt.title('Accuracy vs. K Value')
plt.xlabel('K Value')
plt.ylabel('Accuracy')
plt.xticks(np.arange(1, 21, step=1))
plt.show()

# Combine k values and their corresponding accuracies
k_accuracy_pairs = list(zip(range(1, 21), accuracies))

# Sort by accuracy in descending order and select the top 5
top_5_k = sorted(k_accuracy_pairs, key=lambda x: x[1], reverse=True)[:5]

# Print the top 5 k values with the highest accuracies
print("Top 5 k values with the highest accuracies:")
for k, acc in top_5_k:
    print(f"k = {k}, Accuracy = {acc:.4f}")

print(f"\nTop 5 k confusion matrices")
for k, acc in top_5_k:
    print(f"k = {k}, Confusion matrix:")
    print(confusion_matrices[k - 1])
