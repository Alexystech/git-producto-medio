import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class productoMedio
{
    private static InputStreamReader isr = new InputStreamReader(System.in);
    private static BufferedReader br = new BufferedReader(isr);
    private static List<Integer> almacen_semilas = new ArrayList<Integer>();
    private static List<Integer> almacen_y_columna_1 = new ArrayList<Integer>();
    private static List<Integer> almacen_y_columna_2 = new ArrayList<Integer>();
    private static List<Integer> almacen_producto = new ArrayList<Integer>();
    private static List<Float> almacen_r = new ArrayList<Float>();
    public static void main(String []args) throws IOException
    {
        boolean switch_menu = true;
        while (switch_menu)
        {
            try 
            {
                int semilla_1 = 0;
                int semilla_2 = 0;
                int cantidad_r = 0;
                do
                {
                    System.out.println("Ingresa la semilla x0:");
                    semilla_1 = Integer.parseInt(br.readLine());
                    System.out.println("Ingresa la semilla x1");
                    semilla_2 = Integer.parseInt(br.readLine());
                    System.out.println("Ingresa la cantidad de r:");
                    cantidad_r = Integer.parseInt(br.readLine());

                    if (!is_greater_of_three(semilla_1) || !is_greater_of_three(semilla_2))
                    {
                        JOptionPane.showMessageDialog(null,"error de cantidad");
                    }
                }while(!is_greater_of_three(semilla_1) || !is_greater_of_three(semilla_2));
            
                obtener_r(semilla_1, semilla_2, cantidad_r);

                imprimir_datos(cantidad_r);
                switch_menu = false;
            } catch (Exception e) 
            {
                //TODO: handle exception
                JOptionPane.showMessageDialog(null,"error de tipo");
            }
        }
    }

    private static boolean is_greater_of_three(int semilla)
    {
        String semilla_text = Integer.toString(semilla);
        return (semilla_text.length() > 3)? true : false;
    }

    private static void imprimir_datos(int cantidad_r)
    {
        for (int x = 0; x < cantidad_r; x++)
        {
            System.out.println
            (
                "y"+x+" = ("+
                almacen_y_columna_1.get(x)+")("+
                almacen_y_columna_2.get(x)+") = "+
                almacen_producto.get(x)+" x"+(x+2)+" = "+
                almacen_semilas.get(x)+" r"+(x+1)+" = "+
                almacen_r.get(x)
            );
        }
    }

    private static int obtener_r(int semilla_1,int semilla_2,int cantidad_r)
    {
        if (cantidad_r == 0)
        {
            return 0;
        }
        else
        {
            
            almacen_y_columna_1.add(semilla_1);
            almacen_y_columna_2.add(semilla_2);
            almacen_producto.add(product(semilla_1, semilla_2));
            almacen_r.add((float)new_zen(product(semilla_1, semilla_2)) / 10000);
            almacen_semilas.add(new_zen(product(semilla_1, semilla_2)));

            return obtener_r(semilla_2,new_zen(product(semilla_1, semilla_2)),cantidad_r-1);
        }
    }

    private static int product(int semilla_1,int semilla_2)
    {
        return semilla_1 * semilla_2;
    }

    private static int new_zen(int producto)
    {
        String producto_text = Integer.toString(producto);
        int new_zen_value = 0;

        if ( (producto_text.length()%2) != 0)
        {
            producto_text = "0"+producto_text;
            int limites_inicio_final = (producto_text.length() - 4) / 2;
            String new_zen_text = producto_text.substring(limites_inicio_final,limites_inicio_final+4);
            new_zen_value = Integer.parseInt(new_zen_text);
        }
        else
        {
            int limites_inicio_final = (producto_text.length() - 4) / 2;
            String new_zen_text = producto_text.substring(limites_inicio_final, limites_inicio_final+4);
            new_zen_value = Integer.parseInt(new_zen_text);
        }
        return new_zen_value;
    }
}