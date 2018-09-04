library(e1071)

diabetes_train <- read.table('diabetes.csv', header = TRUE, sep = ',')
diabetes_test <- read.table('diabetes_trial.csv', header = TRUE, sep = ',')

model = naiveBayes(Outcome~., diabetes_train)
prediction = predict(model, diabetes_test, 'raw')

