import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from pandas.plotting import scatter_matrix

url = "iris.csv"
names = ['sepal-length', 'sepal-width', 'petal-length', 'petal-width', 'class']
dataset = pd.read_csv(url, names=names)

# Shape
print(dataset.shape)

# Peek data
# print(dataset.head(5))

# Descriptions
print(dataset.describe())

# Class distribution
print(dataset.groupby('class').size())

# Box and whisker plots
dataset.plot(kind='box', subplots=True, layout=(2, 2), sharex=False, sharey=False)
plt.show()

# Histogram
dataset.hist()
plt.show()

# Scatter plot matrix
scatter_matrix(dataset)
plt.show()
