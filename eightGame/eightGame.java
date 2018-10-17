import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class eightGame extends JFrame{
    public int blank = 0;
    private int moves = 0; 
    private char[] c = new char[9];
    private long start;
    private long end;

    click clicked = new click();
    JFrame frame = new JFrame("8 Game");
    JButton [] buttons = new JButton [9];
    
    void createWindow(){
        eightGame w = new eightGame();
        c = w.createArray();
        
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel p = new JPanel();
        frame.add(p);
        
        for(int i = 0; i < 9; i++){
            if(c[i] == ' ')
                this.blank = i;
            buttons[i] = new JButton(Character.toString(c[i]));
            buttons[i].setPreferredSize(new Dimension(100, 100));
            buttons[i].addActionListener(clicked);
            p.add(buttons[i]);
        }
        
        frame.setResizable(false);
        frame.setVisible(true);
        start = System.nanoTime();

    }
    

    // Check if button near source index is blank if yes update buttons, array, blank, and moves 
    void checkBlank(int source, int check){
        String temp;
        if(check == blank){
        	// Update button labels and blank 
            temp = buttons[check].getText();
            buttons[check].setText(buttons[source].getText());
            buttons[source].setText(temp);
            blank = source;

            // Update the array 
            c[check] = c[source];
            c[source] = ' ';
            
            // Update moves
            moves += 1;

            if (isOrdered()){
            	end = System.nanoTime();
            	int seconds = (int)((end - start)/1000000000.0);
            	JOptionPane.showMessageDialog(null, "You win!" + " Number of moves: " + moves + " Time: " + seconds + " seconds.");
            }
        }
    }
    
    private class click implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if (event.getSource() == buttons[0]){
                checkBlank(0, 1);
                checkBlank(0, 3);
            }
            
            else if (event.getSource() == buttons[1]){
                checkBlank(1, 0);
                checkBlank(1, 2);
                checkBlank(1, 4);
            }
            
            else if (event.getSource() == buttons[2]){
                checkBlank(2, 1);
                checkBlank(2, 5);
            }
            
            else if (event.getSource() == buttons[3]){
                checkBlank(3, 0);
                checkBlank(3, 4);
                checkBlank(3, 6);
            }
            
            else if (event.getSource() == buttons[4]){
                checkBlank(4, 1);
                checkBlank(4, 3);
                checkBlank(4, 5);
                checkBlank(4, 7);
            }
            
            else if (event.getSource() == buttons[5]){
                checkBlank(5, 2);
                checkBlank(5, 4);
                checkBlank(5, 8);
            }
            
            else if (event.getSource() == buttons[6]){
                checkBlank(6, 3);
                checkBlank(6, 7);
            }
            else if (event.getSource() == buttons[7]){
                checkBlank(7, 4);
                checkBlank(7, 6);
                checkBlank(7, 8);
            }
            
            else if(event.getSource() == buttons[8]){
                checkBlank(8, 5);
                checkBlank(8, 7);
            }
        }
    }
    
    // Create a character array 1 - 8 plus 1 blank (' ')
    char[] createArray(){
        for(int i = 0; i < 8; i++){
            c[i] = (char)(i + 1 + '0');
        }
        
        c[8] = ' ';
        
        return shuffle();
    }
    

    char[] shuffle(){
        int index;
        Random random = new Random();
        
        for (int i = c.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                c[index] ^= c[i];
                c[i] ^= c[index];
                c[index] ^= c[i];
            }
        }
        return c;   
    }
    
    // Check to see if the puzzle is solved 
    boolean isOrdered(){
        for(int i = 1; i < 9; i++){
        	// Check each element to see if array is sequential
            if( (Character.getNumericValue(c[i-1]) + 1) == Character.getNumericValue(c[i])){}
            
            // ignore blank
            else if(i == blank){}
            
            // if not sequential or blank 
            else
                return false;
        }

        // Sequential array c
        return true;
    }
    
    public static void main(String[] args){
        eightGame w = new eightGame();
        w.createWindow();
    }
}
