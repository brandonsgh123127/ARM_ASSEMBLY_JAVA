
package hw2;

public class ARM {
    private RegisterBank rb;
    private RAM mb;
    /*
    size_reg- how many bytes
    bits_reg- how many bits in the register
    */
    public ARM(int size_reg, int bits_reg, int size_ram, int bytes_ram){
        rb = new RegisterBank(size_reg,bits_reg);
        mb = new RAM(size_ram,bytes_ram);
    }
    /*
    Where Rd is register and Rn is a value in bits, and a shift in bits,
    SO FAR- ONLY CONVERTS NUMBER TO BINARY VALUE INTO REGISTRY
    
    */
    public void mov(int Rd, int Rn, int bitshift){
        System.out.println("MOV...");
        System.out.println("Value of Rd: r" + Rd);
        System.out.println("Value of Rn: #" + Rn);
        System.out.println("Value of bitshift: " + bitshift);
        /*
        PT 1. INITIALIZING BITS!!!!
            |=======|    //|  |
            |   _   |   // |  |
            |  | |  |      |  |
            |  |_|  |   ___|  |___
            |_______|   |________|
        */
        
        //Both variables used to store into register
        Word Rn1 = new Word(32);
        int[] RntoBits  = new int[Rn1.bits];
        //Used to convert to binary value
        String s = Long.toBinaryString(Rn);
        
        
        //Loop that allows for binary representation
        for(int i = 0; i< s.length(); i++){
            //Sets a reverse value to end of array
            RntoBits[RntoBits.length-1-i]= Integer.valueOf(s.substring(s.length()-1-i, s.length()-1-(i-1)));
        }
        //Sets value of word to Rn in bit form
        Rn1.set(RntoBits);
        //Set the register value into Rd for now
        rb.set(Rd,Rn1);
        
        
        /*
        PT 2.  SHIFT BITS!!!!!!!
            |       ___ |---->
            |     /  /  |---->
            |   /___/   |---->
            |_____ _____|---->
            Rn1 contains the value of Rn currently, but it isn't shifted and
            placed in register.
        */
        //new Word set up
        Word shiftBit = new Word(Rn1.bits);
        /*
        Shifting left!
        */
       if(Integer.toString(bitshift).contains("-")){ //SHIFTING LEFT...
           //Count through each shift
           for(int i = 0; i < Math.abs(bitshift); i++){
               //Loop to rotate each time until it reaches [1]
               int temp = RntoBits[0];//Value stored
               for(int j = 0; j < RntoBits.length-1; j++){
                    try{
                        RntoBits[j]=
                        RntoBits[j+1]; //take whatever is left of j
                    }
                    catch(Exception e){
                        System.out.println("ERROR");
                        temp = RntoBits[0];
                            RntoBits[RntoBits.length-1] = temp;
                            RntoBits[j]=RntoBits[j+1];
                        
                    }
               }
               RntoBits[RntoBits.length-1]= temp;               
            }
       } 
       /*
       Shifting Right!
       */
       else{ //SHIFTING RIGHT...
           
            //Count through each shift
           for(int i = 0; i < bitshift; i++){
               //Loop to rotate each time until it reaches [1]
               int temp = RntoBits[RntoBits.length-1];//Value stored
               for(int j = RntoBits.length-2; j >= 0; j--){
                    try{
                        RntoBits[j+1]=
                        RntoBits[j]; //take whatever is left of j
                    }
                    catch(Exception e){ //CATCHES OUT OF BOUNDS
                        temp = RntoBits[RntoBits.length-1];
                        RntoBits[0] = temp;
                        RntoBits[j]=RntoBits[j-1];
                    }
                }
                RntoBits[0]= temp;
            }
       }
       
       //Finally sets shiftBit value to array and sets registry
       shiftBit.set(RntoBits);
       rb.set(Rd, shiftBit);
    }
    
    public void str(int Rd, int Rn, int o, boolean pre, boolean mod_pre){
        //TO DO
    }
    
    public void ldr(int Rd, int Rn, int o, boolean pre, boolean mod_pre){
        //TO DO
    }
    
    public void add(int Rd, int Rn, int Rc){
        
    }
    
    public void print(){
        //TO DO
    }
    
}
