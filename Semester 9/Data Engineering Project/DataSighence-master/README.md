## Pipeline sequence:


Exploration of the Google Play Store datasets.


Data cleaning & Tidying:

    a. Handling missing values (imputation, dropping, replacing with values from the Play Store).
        1- Missing Ratings were imputed using mean of similar apps with same of number installs
        2- Missing Android Version for the corresponding apps were retrieved from PlayStore
        3- Dropped reviews where there was no actual text review in user_reviews dataset
        
    b. Shifting an unordered row.
    
    c. Switching to single value attributes.
        1- Genres attribute contained two values in many rows. So a 2nd column (Subgenre was created to contain that 2nd value
    
    d. Changing columns' data types.
        1- Rating to be float
        2- Size to be float
        3- Installs to be int 
    
    e. Discretization for `Size` and `Installs` with `SizeRange` and `InstallsRange` respectively.


Data visualization: (We use the following methods for visualizing the data we cleaned from the last step in order to extract useful information)
    
    a. Histogram
    
    b. Scatterplot
    
    c. Bar chart
    
    d. Bar histogram
    
    e. Boxplot
