package Clinic;

public class PaymentMethod {
    //cash, card, which goes to debit or credit
    //asks if they have insurance
        public static final String CASH = "Cash";
        public static final String CARD = "CARD";
        private String method;
        public boolean hasInsurance = false;
        private String cardType;

        public PaymentMethod(String method, boolean hasInsurance){
            setMethod(method);
            this.hasInsurance = hasInsurance;
        }
        void setMethod(String method){
            this.method = method;
        }
        public boolean hasInsurance(){
            return hasInsurance;
        }
        public void setInsurance(boolean hasInsurance){
            this.hasInsurance = hasInsurance;
        }

    
}
