package hw2;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class HW2 extends JFrame {
        
    static int size_reg,bits_reg,size_ram,bytes_ram;  //Used to store for ARM
    public static Dimension d = new Dimension();
    //Creates a JPanel Manager, which keeps track of the frames
       static JPanel manager = new JPanel();
       //Creates timer to update values in registers
       static Timer timer;
       
       //Helps figure out what operation was pressed
        static String operation = "";
       
    

    public static void main(String[] args) {
        System.out.println("Just in case more test cases are required...");
        testCases(new ARM(15,32,15*32,4));
        System.out.println("\n\n\n\n\n");
        System.out.println("Starting now.");
        //Delay
        for(int i = 0; i <3; i++){
            System.out.print(".");
            try{
            TimeUnit.SECONDS.sleep(1);
            }
            catch(Exception e){
            
            }
        }
        System.out.println();
        startupGUI();
        
        
        
        
        

    }
    
    
    public static void startupGUI(){        
        //Creates startup frame
        JFrame startup = new JFrame("Startup...");
        //manager.add(startup);
        startup.setSize(d.width,d.height);
        startup.setLocation(300,200);
        
        //Creating Objects for use
        JLabel size_reg_label = new JLabel("How many registers?");
        TextField size_register = new TextField();
        JLabel bits_reg_label = new JLabel("How many bits per register?");
        TextField bits_register = new TextField();
        JButton finished = new JButton("Create");
        JLabel ignore = new JLabel();
        //Setting Bounds per each object
        size_register.setBounds(150,200,150,25);
        bits_register.setBounds(350,200,150,25);
        size_reg_label.setBounds(150,150,200,25);
        bits_reg_label.setBounds(350,150,200,25);
        finished.setBounds(225,250,125,25);
        finished.setSize(125, 25);
        
        finished.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Set up values to pass on to new frame...
                size_reg = Integer.parseInt(size_register.getText().toString());
                bits_reg =  Integer.parseInt(bits_register.getText().toString());
                try{
                size_ram = size_reg * bits_reg;
                bytes_ram = bits_reg / 8;
                startup.dispose();
                MyMainGUI();
                System.out.println("RUNNING GUI!");
                }
                catch(NullPointerException ex){
                    System.out.println("error!");
                }
            }
        });
        //Add on the bits
        startup.add(finished);
        startup.add(size_register);
        startup.add(size_reg_label);
        startup.add(bits_reg_label);
        startup.add(bits_register);
        startup.add(ignore);
  
        //pack frame for updated display
        startup.setExtendedState(MAXIMIZED_BOTH);
        startup.pack();
        startup.setVisible(true);
        startup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
                
    }
    
    /**
     * ARM gets the necessary values, main frame...
     */
    public static void MyMainGUI(){
        
        
        
        
        //Creates ARM object...
        ARM a = new ARM(size_reg,bits_reg,size_ram, bytes_ram);
        JFrame main = new JFrame("ARM_IN_JAVA");
        main.setSize(d.width,d.height);
        main.setLocation(300,200);
        
        
        //Creates register text fields
        TextField[] registers = new TextField[size_reg];
        JLabel[] reg_labels = new JLabel[size_reg]; //labels
        int count = 15;
        for(int i = 0 ; i < registers.length;i++){
            registers[i] = new TextField("00000000000000000000000000000000");
            reg_labels[i] = new JLabel("r" + i);
            registers[i].setBounds(150,count,250,25);
            reg_labels[i].setBounds(125,count,25,25);
            registers[i].setEditable(false);
            count+=30;
        }
        
         
         
        //Creating new Objects
        JButton MOV = new JButton("MOV");
        JButton STR = new JButton("STR");
        JButton LDR = new JButton("LDR");
        JButton ADD = new JButton("ADD");
        JButton EXIT = new JButton("EXIT");
        JButton ENTER = new JButton("Press When Finished!!!");
        //Buttons appear when you press on one of the buttons above
        TextField Rd = new TextField();
        JLabel Rd_label = new JLabel("Rd:");
        TextField Rn = new TextField();
        JLabel Rn_label = new JLabel("Rn:");
        TextField bitshift = new TextField();
        JLabel bitshift_label = new JLabel("Bitshift:");
        TextField offset = new TextField();
        JLabel offset_label = new JLabel("Offset:");
        JCheckBox pre = new JCheckBox("pre-offset?");
        JCheckBox mod_pre = new JCheckBox("'!'?");
        TextField Rc = new TextField();
        JLabel Rc_label = new JLabel("Rc:");
        JLabel Sp_label = new JLabel("Sp:" + a.sp);
        JLabel Pc_label = new JLabel("Pc:" + a.pc);
        
        //Setting Bounds/Size
        MOV.setBounds(100, 1000, 150, 50);
        STR.setBounds(300, 1000, 150, 50);
        LDR.setBounds(500, 1000, 150, 50);
        ADD.setBounds(700, 1000, 150, 50);
        EXIT.setBounds(1700, 1000, 150, 50);
        ENTER.setBounds(900, 300, 200, 50);
        Rd.setBounds(800, 200, 150, 25);
        Rd_label.setBounds(800, 150, 25, 25);
        Rn.setBounds(1000, 200, 150, 25);
        Rn_label.setBounds(1000, 150, 100, 40);
        bitshift.setBounds(1200, 200, 150, 25);
        bitshift_label.setBounds(1200, 150, 50, 25);
        offset.setBounds(1400, 200, 150, 25);
        offset_label.setBounds(1400, 150, 50, 25);
        pre.setBounds(1600, 200, 150, 50);
        mod_pre.setBounds(1800, 200, 150, 50);
        Rc.setBounds(1200, 200, 150, 25);
        Rc_label.setBounds(1200, 150, 50, 25);
        Sp_label.setBounds(1700, 800, 50, 25);
        Pc_label.setBounds(1700, 830, 50, 25);

        //Initially Create the timer...
         timer = new Timer(1000, new ActionListener(){      // Timer 1 second
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < registers.length; i++){
                    int[] bits = a.rb.get(i).get();
                    String s = "";
                    for(int j: bits){
                        s+= Integer.toString(j);
                    }
                    
                    registers[i].setText(s);
                }
                Sp_label.setText("Sp: "+ a.sp);
                Pc_label.setText("Pc: "+ a.pc);

            }
        });
         
         timer.start();
        
        
        //ADDING ACTIONS FOR EACH BUTTON
        
        MOV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rd.setVisible(true);
                Rd_label.setVisible(true);
                Rn.setVisible(true);
                Rn_label.setVisible(true);
                Rn_label.setText("Rn: ");
                bitshift.setVisible(true);
                bitshift_label.setVisible(true);
                offset.setVisible(false);
                offset_label.setVisible(false);
                pre.setVisible(false);
                mod_pre.setVisible(false);
                Rc.setVisible(false);
                Rc_label.setVisible(false);
                operation = "MOV";
            }
        });
        STR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rd.setVisible(true);
                Rd_label.setVisible(true);
                Rn.setVisible(true);
                Rn_label.setVisible(true);
                Rn_label.setText("Rn(Write sp):");
                bitshift.setVisible(false);
                bitshift_label.setVisible(false);
                offset.setVisible(true);
                offset_label.setVisible(true);
                pre.setVisible(true);
                mod_pre.setVisible(true);
                Rc.setVisible(false);
                Rc_label.setVisible(false);
                operation = "STR";
            }
        });
        LDR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rd.setVisible(true);
                Rd_label.setVisible(true);
                Rn.setVisible(true);
                Rn_label.setVisible(true);
                Rn_label.setText("Rn(Write sp):");
                bitshift.setVisible(false);
                bitshift_label.setVisible(false);
                offset.setVisible(true);
                offset_label.setVisible(true);
                pre.setVisible(true);
                mod_pre.setVisible(true);
                Rc.setVisible(false);
                Rc_label.setVisible(false);
                operation = "LDR";            }
        });
        ADD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Rd.setVisible(true);
                Rd_label.setVisible(true);
                Rn.setVisible(true);
                Rn_label.setVisible(true);
                Rn_label.setText("Rn: ");
                bitshift.setVisible(false);
                bitshift_label.setVisible(false);
                offset.setVisible(false);
                offset_label.setVisible(false);
                pre.setVisible(false);
                mod_pre.setVisible(false);
                Rc.setVisible(true);
                Rc_label.setVisible(true);
                
                operation = "ADD";
            }
        });
        EXIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < JFrame.getWindows().length;i++){
                    timer.stop();
                    JFrame.getWindows()[i].dispose();
                }
            }
        });
        ENTER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(operation == "MOV"){
                    a.mov(Integer.parseInt(Rd.getText().toString()),
                          Integer.parseInt(Rn.getText().toString()),
                          Integer.parseInt(bitshift.getText().toString()));
                }
                else if(operation == "STR"){
                    
                    try{
                        if("sp".equals(Rn.getText().toString().toLowerCase()))
                            Rn.setText(Integer.toString(a.sp));
                    }
                    catch(Exception ex){
                        
                    }
                    
                    a.str(Integer.parseInt(Rd.getText().toString()),
                          Integer.parseInt(Rn.getText().toString()),
                          Integer.parseInt(offset.getText().toString()),
                          pre.isSelected(),
                          mod_pre.isSelected());
                }
                else if(operation == "LDR"){
                     try{
                        if("sp".equals(Rn.getText().toString().toLowerCase()))
                            Rn.setText(Integer.toString(a.sp));
                    }
                    catch(Exception ex){
                        
                    }
                    
                          a.ldr(Integer.parseInt(Rd.getText().toString()),
                          Integer.parseInt(Rn.getText().toString()),
                          Integer.parseInt(offset.getText().toString()),
                          pre.isSelected(),
                          mod_pre.isSelected());
                }
                else if(operation == "ADD"){
                    a.add(Integer.parseInt(Rd.getText().toString()),
                          Integer.parseInt(Rn.getText().toString()),
                          Integer.parseInt(Rc.getText().toString()));
                }
                
                
            }
        });
        
        
        //prepping to add objects to JFrame
        main.setLayout(null);
        main.add(MOV);
        main.add(STR);
        main.add(LDR);
        main.add(ADD);
        main.add(EXIT);
        main.add(ENTER);
        main.add(Rd);
        main.add(Rd_label);
        main.add(Rn);
        main.add(Rn_label);
        main.add(bitshift);
        main.add(bitshift_label);
        main.add(offset);
        main.add(offset_label);
        main.add(pre);
        main.add(mod_pre);
        main.add(Rc);
        main.add(Sp_label);
        main.add(Pc_label);
        
        //Set initial visibility to false...
        Rd.setVisible(false);
        Rd_label.setVisible(false);
        Rn.setVisible(false);
        Rn_label.setVisible(false);
        bitshift.setVisible(false);
        bitshift_label.setVisible(false);
        offset.setVisible(false);
        offset_label.setVisible(false);
        pre.setVisible(false);
        mod_pre.setVisible(false);
        Rc.setVisible(false);
        Rc_label.setVisible(false);
        Sp_label.setVisible(true);
        Pc_label.setVisible(true);
        for(TextField i: registers)
            main.add(i);
        for(JLabel i: reg_labels)
            main.add(i);
        
        

        //pack frame for updated display
        main.setExtendedState(MAXIMIZED_BOTH);
        main.pack();
        main.setVisible(true);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    
    public static void testCases(ARM a){
        
        
        //r0,r1,r2,r5 have values
        a.mov(0, 0x00000001, 0);//mov 1 into r0
        
        a.mov(1, 0x00000015, 4); //Moved 21 into r1 with a 4 bit shift
        //00000000000000000000000000010101 --> 00000000000000000000000000010101
        System.out.println("\n");
        a.mov(2,0x00000F10,2);//Moved 3856 into r2
        System.out.println("\n");
        
        a.mov(5, 0x00000011, -30); // 17 into r5
        System.out.println("\n");
        
        System.out.println("THIS IS BEFORE STORING/LOADING \n"
                            + "But We Have Stored Into r0, r1,r2,r5");
        a.print();
        System.out.println("\n");
        
                                                
        a.str(0, a.sp, 12, false, false);//Post-offset, stores in current sp, 
                                            //then changes sp  
                                            //value 1
        a.str(0,a.sp,-12,true,true);//pre-offset!!!!!!  Sp has offset of -12*8
                                            //value 0
        //
        a.mov(0, 14, -3);//Moves 14 with a bitshift of LSL 3 into R0
        System.out.println("THIS IS AFTER MOVING R0");
        a.print();
        
        a.str(0,a.sp,0,true,true); //Pre-offset!!  Sp stays the same
                                      //value 14 LSL 3
        a.str(2,a.sp,12,true,false);  //Pre-offset, moves sp 12*8, but goes back
                                     //value 3856
        a.mov(2, 4, 0);//no bitshift
        System.out.println("THIS IS AFTER MOVING R2, VALUE 4:  ");
        a.print();
        
        a.ldr(2, a.sp, 12, true, false);  //LOAD R2, which is 3856
        System.out.println("THIS IS AFTER LOADING R2, WHICH IS 3856:  ");
        a.print();
        
        a.mov(0, 2563, 3);
        System.out.println("THIS IS AFTER MOVING R0, WHICH IS 2563 LSL 3:  ");
        a.print();
        
        a.ldr(0, a.sp, 0, true, true);  //LOAD R0, which is 14LSL3
        System.out.println("THIS IS AFTER LOADING R0, WHICH IS 14 LSL 3:  ");
        a.print();
       
    }
    
}
