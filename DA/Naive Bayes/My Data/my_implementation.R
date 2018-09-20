library(e1071)

golf_train <- read.table('golf_train.csv', header = TRUE, sep = ',')
golf_test <- read.table('golf_test.csv', header = TRUE, sep = ',')

model = naiveBayes(Play.golf~., data = golf_train)
prediction = predict(model, golf_test)

#> setwd("~/Documents")
#> library(caTools)
#> library(ggplot2)
#> library(e1071)
#> golf_train <- read.table('golf_train.csv', header = TRUE, sep = ',')
#> View(golf_train)
#> golf_test <- read.table('golf_test.csv', header = TRUE, sep = ',')
#> View(golf_test)
#> model = naiveBayes(Play.golf~., data = golf_train)
#> model = naiveBayes(Play.golf~., data = golf_train)
#> View(model)
#> model

#Naive Bayes Classifier for Discrete Predictors

#Call:
#  naiveBayes.default(x = X, y = Y, laplace = laplace)

#A-priori probabilities:
#  Y
#No       Yes 
#0.3571429 0.6428571 

#Conditional probabilities:
#  Day
#Y            D1       D10       D11       D12       D13       D14        D2        D3        D4        D5        D6        D7
#No  0.2000000 0.0000000 0.0000000 0.0000000 0.0000000 0.2000000 0.2000000 0.0000000 0.0000000 0.0000000 0.2000000 0.0000000
#Yes 0.0000000 0.1111111 0.1111111 0.1111111 0.1111111 0.0000000 0.0000000 0.1111111 0.1111111 0.1111111 0.0000000 0.1111111
#Day
#Y            D8        D9
#No  0.2000000 0.0000000
#Yes 0.0000000 0.1111111

#Outlook
#Y      Overcast      Rain     Sunny
#No  0.0000000 0.4000000 0.6000000
#Yes 0.4444444 0.3333333 0.2222222

#Temperature
#Y          Cool       Hot      Mild
#No  0.2000000 0.4000000 0.4000000
#Yes 0.3333333 0.2222222 0.4444444

#Humidity
#Y          High    Normal
#No  0.8000000 0.2000000
#Yes 0.3333333 0.6666667

#Wind
#Y        Strong      Weak
#No  0.6000000 0.4000000
#Yes 0.3333333 0.6666667

#> prediction = predict(model, golf_test)
#> prediction
#[1] Yes Yes Yes Yes No 
#Levels: No Yes