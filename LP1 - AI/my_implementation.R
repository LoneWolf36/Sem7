dataset = read.csv('my_dataset.csv')

library(caTools)
library(ggplot2)
library(e1071)

set.seed(123)
my_split = sample.split(dataset, SplitRatio = 0.7)

training_set = subset(dataset, my_split == TRUE)
test_set = subset(dataset, my_split == FALSE)

model = naiveBayes(dataset$Age, dataset$YearsExperience, dataset$Salary, training_set)

ggplot()
geom_point(aes(training_set$YearsExperience, test_set$Salary), color = 'red')
geom_line()