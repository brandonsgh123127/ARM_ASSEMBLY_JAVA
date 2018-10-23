package hw2;

public class HW2 {

    public static void main(String[] args) {
        /*  (1) write a short program using the java ARM class that is like this C++ code:
            int x = 13;
            int y = 14;
            int z = 16;
            z = x+y+z;
        Note: assume 32bit ARM and RAM should have 4 byte alignments (4*bytes)
            (2) call the print method periodically to check your work or use the debugger
        */
        //10 registers, 4 bytes, 2^10 size ram, 4 bytes
        ARM a = new ARM(15,32,2^10,4);
        a.mov(0, 0x00000001, 0);
        
        a.mov(1, 0x00000015, 4); //Moved 21 into r1 with a 4 bit shift
        //00000000000000000000000000010101 --> 00000000000000000000000000010101
        System.out.println("\n");
        a.mov(2,0x00000F10,2);//Moved 3856 into r2
        System.out.println("\n");
        
        a.mov(5, 0x00000011, -30); //
        System.out.println("\n");
        
        System.out.println("THIS IS BEFORE STORING/LOADING");
        a.print();
        System.out.println("\n");
        //store into r3 of r0 and offset of 4 bits
        a.str(3, 0, 4, true, true);
        System.out.println("THIS IS AFTER STORING/LOADING");
        a.print();
        
        
        /*
        
        NEED TO GET STR TO WORK....  HOW DO I DO MULTIPLE REGISTER BANKS
        */ 

    }
    
}
