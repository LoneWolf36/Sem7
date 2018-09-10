import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from sklearn import datasets

iris_dataset = datasets.load_iris()
X = iris_dataset.data[:, :2]
Y = iris_dataset.target

print(X)
print(Y)

