# PayClipCommandLine
Command line tool

## Build
mvn clean package

## Usage

Under target directory

java -jar Application-XXX-jar-with-dependecies.jar [Options]

### [Options]

        ADD: <user_id> ADD <transaction_json>
                Add a transaction to the user specified in <user_id> using the information specified in <transaction_json>
        
        SHOW: <user_id> <transaction_id>
                Return the transaction specified in the <transaction_id>
                
        LIST: <user_id> LIST 
                Print all the transactions associated with the user specified by <user_id>
                
        SUM: <user_id> SUM 
                Sum all the transactions associated with the user specified by <user_id>


