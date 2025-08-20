# Oving 3 INFT2003 - Pandas og Dataanalyse
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# Oppgave 1
df = pd.read_csv('H1.csv')

# Grupperer alle landene og teller alle hendelsene
country_counts = df.groupby('Country')['Country'].count()

# Viser de 10 landene med flest kunder
top_10_countries = country_counts.nlargest(10)

print(f"Oppgave 1:\n{top_10_countries}\n\n")


# Oppgave 2
df = pd.read_csv('H1.csv')

# Filterer bort rader hvor bookings er kansellert
df_not_canceled = df[df['IsCanceled'] == 0]

# Oppretter en ny kolonne for pris. (Antar at ADR (Average Daily Rate) skal ganges med summen av netter i helg og ukedager)
df_not_canceled.loc[:, 'TotalRevenue'] = df_not_canceled['ADR'] * (df_not_canceled['StaysInWeekendNights'] + df_not_canceled['StaysInWeekNights'])

# Grupperer etter MarketSegment og velger sum av den nye pris-kolonnen
revenue_by_segment = df_not_canceled.groupby('MarketSegment')['TotalRevenue'].sum()

print(f"Oppgave 2:\n{revenue_by_segment}\n\n")


# Oppgave 3
df = pd.read_csv('H1.csv')

# Finner start og slutt verdiene for intervallet
max_adr = df['ADR'].max()
min_adr = df['ADR'].min()

# Velger størrelse og forhold 10:6, med 40 bins og gjennomsiktlighet 0.7
plt.figure(figsize=(10, 6))
plt.hist(df['ADR'], bins=40, range=(min_adr, max_adr), alpha=0.7)

# Tittel og label etc.
plt.title('Distribution of Room Prices (ADR)', fontsize=14)
plt.xlabel('ADR (Average Daily Rate)', fontsize=12)
plt.ylabel('Frequency', fontsize=12)

plt.show()


# Oppgave 4
df = pd.read_csv('H1.csv')

# Filterer bort data som ikke er i 2016
df_2016 = df[df['ArrivalDateYear'] == 2016]

# Konverter månedsnavn til en numerisk verdi (1-12) for å plotte månedene kronologisk
df_2016.loc[:, 'ArrivalDateMonth'] = pd.to_datetime(df_2016['ArrivalDateMonth'], format='%B').dt.month

# Beregner gjennom snitt av AVD for hver måned
monthly_avg_adr = df_2016.groupby('ArrivalDateMonth')['ADR'].mean()

# Teller hendelser av kanselleringer for hver måned
monthly_cancellations = df_2016[df_2016['IsCanceled'] == 1].groupby('ArrivalDateMonth')['IsCanceled'].count()

# Plotter linjediagrammene i samme figur
plt.figure(figsize=(10, 6))
plt.plot(monthly_avg_adr.index, monthly_avg_adr.values, label='Average ADR', marker='o')
plt.plot(monthly_cancellations.index, monthly_cancellations.values, label='Number of Cancellations', marker='o')
plt.title('Average Room Price (ADR) and Cancellations per Month in 2016', fontsize=14)
plt.xlabel('Month', fontsize=12)
plt.ylabel('Value', fontsize=12)
plt.legend()

# Setter navn igjen på månedene på x-aksen
plt.xticks(ticks=monthly_avg_adr.index, labels=['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'])

plt.show()

# Kontrollerer verdier mot figur
print(f"Oppgave 4:\n{df.shape}")
print(f"\n{df_2016[df['IsCanceled'] == 1].groupby('ArrivalDateMonth')['IsCanceled'].count()}")
print(f"\n{df_2016.groupby('ArrivalDateMonth')['ADR'].mean()}")
