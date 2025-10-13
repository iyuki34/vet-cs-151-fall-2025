package Clinic;

public class PaymentMethod {
    //cash, card, which goes to debit or credit
    //asks if they have insurance
        private String method;

        public PaymentMethod(String method){
            setMethod(method);
        }
        void setMethod(String method){
            this.method = method;
        }

    
}
