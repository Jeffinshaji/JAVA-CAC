import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class data_cleaner
{
    private String[][] df;
    private String [] column;
    private int row;
    data_cleaner()
    {
        this.df= new String[1][];
    }
    data_cleaner(String filename)
    {
        count_row(filename);
        data_frame(filename);
    }

//count Row

    private void count_row(String filename)
    {
        try{
           this.row=-1;
           File file = new File(filename);
           Scanner scanner = new Scanner(file);
           while(scanner.hasNextLine())
           {
            String line = scanner.nextLine();
            this.row++;
           }
        }catch(Exception e){
            System.out.println("cant read this file");
            e.printStackTrace();
        }
    }

//To create data frame

    private void data_frame(String filename)
    {
        String[][] temp = new String[this.row][];
        int counter = 0;
        try{
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            for (int i=0;i<(temp.length+1);i++)
            {
                String[] line = scanner.nextLine().split(",");
                if(i>0)
                {
                    temp[counter] = line;
                    counter++;
                }
                else
                {
                    this.column =line;
                }
            }
        }catch(IOException e){
            System.out.println("cant read this file!");
            e.printStackTrace();
        }
        this.df=temp;
    }

//To print columns

    public void print_columns()
    {
        for (String i:this.column)
        {
            System.out.println(i);
        }
    }

//To print dataset

    public void print_dataset()
    {
        for (String column : this.column)
        {
            System.out.print(column);
         }
            System.out.println();
            for (String[]row:this.df)
            {
                for (String value:row)
                {
                    if(value.isEmpty())
                    {
                        System.out.print("NaN");
                    }else
                    {
                        System.out.print(value);
                    }
                }
                System.out.println();
            }
    }

//To print data

    public String[] print_data(String column) 
    {
        String[] list = new String[this.row];
        int counter = 0;
        int index = -1;
        for (int i = 0; i < this.column.length; i++) 
        {
            if (this.column[i].equals(column)) 
            {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = 0; i < this.row; i++) 
            {
                list[counter] = this.df[i][index];
                counter++;
            }
        } else 
        {
            System.out.println("There is no such column.");
        }
        return list;
    }

//To check null values

    public void isnull_sum() 
    {
        for (String i : this.column) 
        {
            int nullCounter = 0; 
            String[] columnData = print_data(i);
            for (String j : columnData) 
            {
                if (j.equals("")) 
                {
                    nullCounter++;
                }
            }
            System.out.println(i + " : " + Integer.toString(nullCounter));
        }
    }

//To convert the cleaned data to new_csv file

    public void to_csv(String filename) 
    {
        try {
            FileWriter file = new FileWriter(filename, true);
            for (int i = 0; i < this.column.length; i++) 
            {
                if (i == 0) 
                {
                    file.append(this.column[i]);
                } else 
                {
                    file.append("," + this.column[i]);
                }
            }
        file.append("\n");
        for (int i = 0; i < this.df.length; i++) 
        {
            for (int j = 0; j < this.df[i].length; j++)
            {
                if (j == 0) 
                {
                    file.append(this.df[i][j]);
                } else 
                {
                    file.append("," + this.df[i][j]);
            }
            }
            file.append("\n");
        }
        file.close();
        } catch (IOException e)
        {
            System.out.println("Can't write into this file.");
            e.printStackTrace();
        }
    }  
public int row_count(){
    return this.row;
}
}
