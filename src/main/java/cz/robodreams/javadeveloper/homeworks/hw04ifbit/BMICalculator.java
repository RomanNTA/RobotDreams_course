package cz.robodreams.javadeveloper.homeworks.hw04ifbit;

public class BMICalculator {

    /**
     * Implement according to https://cs.wikipedia.org/wiki/Index_t%C4%9Blesn%C3%A9_hmotnosti table "BMI podle WHO"
     *
     *
     *
     * Category 	BMI (kg/m2)
     * (from, to>     from - exclusive, to - inclusive
     * 	from 	to
     * Velmi vážná podvýživa 	-inf	15
     * Vážná podvýživa 	15 	16
     * Podvýživa 	16 	18,5
     * Normální 	18,5 	25
     * Nadváha 	25 	30
     * Nadváha I. stupně (Střední obezita) 	30 	35
     * Nadváha II. stupně (Vážná obezita) 	35 	40
     * Nadváha III. stupně (Velmi vážná obezita) 	40 	+inf
     *
     *
     * @param weight in kilograms
     * @param height in cm
     * @return bmi text meanings by BMI who, example "Podvýživa"
     */
    public String calculate(int weight, int height) {
        String result = "Velmi vážná podvýživa";
        // code your solution here

        // převod na metry
        double dHeight = height / 100.0;

        // Výpočet BMI podle vzorce
        double dBmi = weight / (dHeight * dHeight);

        // Kontrolní výpis
//        System.out.println("Zadaní ...................");
//        System.out.println("height   = " + height);
//        System.out.println("weight   = " + weight);
//        System.out.println("dHeight  = " + dHeight);
//        System.out.println("BMI      = " + dBmi);

        if (dBmi < 0) {
            result = "Chyba zadání. Prosím, zkontroluj hmotnost a výšku.";

        } else if (dBmi > 0 && dBmi <= 15) {
            result = "Velmi vážná podvýživa";

        } else if (dBmi > 15 && dBmi <= 16 ){
            result = "Vážná podvýživa";

        } else if (dBmi > 16 && dBmi <= 18.5) {
            result = "Podvýživa";

        } else if (dBmi > 18.5 && dBmi <= 25){
            result = "Normální";

        } else if (dBmi > 25 && dBmi <= 30) {
            result = "Nadváha";

        } else if (dBmi > 30 && dBmi <= 35) {
            result = "Nadváha I. stupně (Střední obezita)";

        } else if (dBmi > 35 && dBmi <= 40) {
            result = "Nadváha II. stupně (Vážná obezita)";

        } else if (dBmi > 40) {
            result = "Nadváha III. stupně (Velmi vážná obezita)";
        }

        // assign your result to result variable
        //======
        return result;
    }

}
