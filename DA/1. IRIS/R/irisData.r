trial <- read.csv("/home/dhruvatara/Downloads/iris.csv") #read the dataset
head(trial) #display the first few rows of the dataser

summary(trial$sepal_length) #finds the min, max, mean, 1st QT and 3rd QT for each attribute
summary(trial$sepal_width)
summary(trial$petal_length)
summary(trial$petal_width)


hist(trial$sepal_length) #finds the histogram of each attribute
hist(trial$petal_length)
hist(trial$sepal_width)
hist(trial$petal_length)
dev.off()

sd(trial$sepal_length) #finds the standard deviation for each attribute
sd(trial$sepal_width)
sd(trial$petal_length)
sd(trial$petal_width)

boxplot(sepal_length ~ sepal_width,data = trial, xlab = "width", ylab = "length",main = "Sepal Data") #boxplot for sepal length vs sepal width
boxplot(petal_length ~ petal_width,data = trial, xlab = "width", ylab = "length",main = "Petal Data") #boxplot for petal length vs petal width

#boxplot for petal length vs petal width in virginica
virginica <- subset(trial, species == "virginica", select = c(petal_length,petal_width))
boxplot(main = "Petal length and width in virginca species",virginica$petal_length~virginica$petal_width, xlab="Width", ylab="Length")

#boxplot for petal length vs petal width in setosa
setosa <- subset(trial, species == "setosa", select = c(petal_length,petal_width))
boxplot(main = "Petal length and width in setosa species",setosa$petal_length~setosa$petal_width, xlab="Width", ylab="Length")

#boxplot for petal length vs petal width in versicolor
versicolor <- subset(trial, species == "versicolor", select = c(petal_length,petal_width))
boxplot(main = "Petal length and width in versicolor species",versicolor$petal_length~versicolor$petal_width, xlab="Width", ylab="Length")