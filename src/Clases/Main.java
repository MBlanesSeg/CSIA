

import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;


public class Main {

    public static void main(String[] args) {
        //Main main = new Main();
        interfaz objeto = new interfaz();


    }

}


class interfaz extends JFrame implements ActionListener, KeyListener {

    Timer temporizador = new Timer(1000, this);
    Timer duracion_plus = new Timer(500, this);


    operaciones numeros = new operaciones();


    JLabel fondo, puntaje, pregunta, multiplo, tempo, plus, acertado, game_over, manzana, label1, label2; //fondo= imagen de paisaje / puntaje= texto que da el puntaje / pregunta= texto que da la pregunta
    JTextField respuesta; //caja de texto para ingresar la respuesta
    JButton por1, por2, por3, por4, por5, por6, por7, por8, por9, por10, por11, por12; //botones de cada operacion
    JButton abrir, cerrar, iniciar;  //con abrir abro el menú de ooperaciones y con el boton cerrar se cierra dicho menú.
    JPanel panel_fondo, panel_puntaje, panel_pregunta, panel_operaciones, gameOver, panel1, panel2;


    int puntuacion = 0; //variable para llevar el conteo de la puntuación.
    int operacion = 1;
    int numero_aleatorio = 0;
    int contador = 10;
    boolean estado = true;
    boolean estado_acertado = true;
    int mayor_puntaje = 0;
    int puntajes[] = new int[1000]; //en este array se guardan los puntajes
    int i=0; //con esta i vamos a recorrer cada celda del array

    Color colores[] = {new Color(0, 150, 0), new Color(255, 200, 0), new Color(255, 0, 0)};





    public interfaz() {
        super("Multiplicación App"); //titulo a la ventana.
        getContentPane().setBackground(Color.white); //Color  blanco al fondo( contenedor)
        setSize(1100, 650); //tamaño de la ventana
        setLocationRelativeTo(null); //Cuando inicie la App aparecerá en el centro del monitor
        setDefaultCloseOperation(EXIT_ON_CLOSE); //cuando le doy click a la "x" de la ventaja se deja de ejecutar la App.
        setLayout(null); //No le establezco un Layout o diseño especifico sino que lo dejo libre.
        setResizable(false); //NO se puede modificar las dimensiones de la ventana.
        componentes_graficos(); //llamo al método que se encarga de crear cada componente gráfico.
        panel_operaciones.setVisible(false);
        temporizador.stop();
        duracion_plus.stop();

        tempo.setForeground(colores[0]);


        setVisible(true); //Hago que sea visible la ventana.


    }//constructor

    private void componentes_graficos() {
        /*  Componentes para el panel*/
        panel_fondo = new JPanel();
        panel_fondo.setLayout(null);
        panel_fondo.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0))); //borde de linea color negro
        panel_fondo.setBounds(0, 0, getWidth() - 8, getHeight() - 30);
        panel_fondo.setBackground(Color.white);

        panel_puntaje = new JPanel();
        panel_puntaje.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0))); //borde de linea color negro
        panel_puntaje.setBounds(10,10,180,45);
        panel_puntaje.setBackground(Color.white);
        panel_puntaje.setLayout(null);

        panel_pregunta = new JPanel();
        panel_pregunta.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0))); //borde de linea color negro
        panel_pregunta.setBounds(360,200, 220, 35);
        panel_pregunta.setBackground(Color.white);
        panel_pregunta.setLayout(null);

        panel_operaciones = new JPanel();
        panel_operaciones.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0))); //borde de linea color negro
        panel_operaciones.setBounds(15,10,150,600);
        panel_operaciones.setBackground(Color.white);
        panel_operaciones.setLayout(null);

        gameOver = new JPanel();
        gameOver.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0))); //borde de linea color negro
        gameOver.setBounds(20, 20, 900, 450);
        gameOver.setBackground(new Color(244, 240, 190));
        gameOver.setLayout(null);
        gameOver.setVisible(true);

        panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0))); //borde de linea color negro
        panel1.setBounds(320, 210, 230, 45); //ubicación
        panel1.setBackground(new Color(219, 148, 67));
        panel1.setLayout(null);
        gameOver.add(panel1);

        label1 = new JLabel();
        label1.setText("PUNTAJE MAS ALTO: 0");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setBounds(0, 5, 220, 30);
        label1.setBackground(Color.white);
        label1.setFont(new Font("Arial", Font.BOLD, 16));
        panel1.add(label1);


        panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0))); //borde de linea color negro
        panel2.setBounds(320, 150, 230, 45); //ubicación
        panel2.setBackground(new Color(219, 148, 67));
        panel2.setLayout(null);
        gameOver.add(panel2);

        label2 = new JLabel();
        label2.setText("PUNTAJE: 0");
        label2.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setBounds(25, 5, 200, 30);
        label2.setBackground(Color.white);
        label2.setFont(new Font("Arial", Font.BOLD, 20));
        panel2.add(label2);




        /*Componentes para las etiquetas*/
        ImageIcon paisaje = new ImageIcon(getClass().getResource("/Imagenes/paisaje.jpg"));
        fondo = new JLabel();
        fondo.setBounds(80,35, 950,550);
        fondo.setIcon(new ImageIcon(paisaje.getImage().getScaledInstance(fondo.getWidth(), fondo.getHeight(), Image.SCALE_SMOOTH)));
        fondo.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));


        puntaje = new JLabel();
        puntaje.setText("PUNTAJE : " + puntuacion);
        puntaje.setBounds(10, 6, 200, 35);
        puntaje.setBackground(Color.white);
        puntaje.setFont(new Font("Arial", Font.BOLD, 20));

        pregunta = new JLabel();
        //  pregunta.setText(operacion +" x "+numeros.numero());
        pregunta.setText("");
        pregunta.setHorizontalAlignment(SwingConstants.CENTER);
        pregunta.setBounds(50, 5, 115, 30);
        pregunta.setBackground(Color.white);
        pregunta.setFont(new Font("Arial", Font.BOLD, 20));
        pregunta.setVisible(false);

        multiplo = new JLabel();
        multiplo.setText("Multiplicación de  " + operacion + " ");
        multiplo.setHorizontalAlignment(SwingConstants.CENTER);
        multiplo.setBounds(450, 5, 200, 30);
        multiplo.setBackground(Color.white);
        multiplo.setFont(new Font("Arial", Font.BOLD, 20));

        tempo = new JLabel();
        tempo.setText(contador + "");
        tempo.setHorizontalAlignment(SwingConstants.CENTER);
        tempo.setBounds(700, 40, 200, 90);
        tempo.setBackground(Color.white);
        tempo.setFont(new Font("Arial", Font.BOLD, 90));

        ImageIcon unidad = new ImageIcon(getClass().getResource("/Imagenes/plus++.jpg"));
        plus = new JLabel();
        plus.setBounds(85, 260, 80, 80);
        plus.setIcon(new ImageIcon(unidad.getImage().getScaledInstance(plus.getWidth(), plus.getHeight(), Image.SCALE_SMOOTH)));
        plus.setVisible(false);

        acertado = new JLabel();
        acertado.setBounds(880, 480, 30, 30);
        // acertado.setIcon(new ImageIcon(correcto.getImage().getScaledInstance(acertado.getWidth(), acertado.getHeight(), Image.SCALE_SMOOTH)));
        acertado.setVisible(false);


        ImageIcon game = new ImageIcon(getClass().getResource("/Imagenes/game_over.PNG"));
        game_over = new JLabel();
        game_over.setBounds(235, 40, 450, 90);
        game_over.setIcon(new ImageIcon(game.getImage().getScaledInstance(game_over.getWidth(), game_over.getHeight(), Image.SCALE_SMOOTH)));

        ImageIcon fruta = new ImageIcon(getClass().getResource("/Imagenes/manzana2.PNG"));

        manzana = new JLabel();
        manzana.setBounds(25, 10, 80, 95);
        manzana.setIcon(new ImageIcon(fruta.getImage().getScaledInstance(manzana.getWidth(), manzana.getHeight(), Image.SCALE_SMOOTH)));
        gameOver.add(manzana);

        manzana = new JLabel();
        manzana.setBounds(790, 10, 80, 95);
        manzana.setIcon(new ImageIcon(fruta.getImage().getScaledInstance(manzana.getWidth(), manzana.getHeight(), Image.SCALE_SMOOTH)));
        gameOver.add(manzana);

        manzana = new JLabel();
        manzana.setBounds(790, 340, 80, 95);
        manzana.setIcon(new ImageIcon(fruta.getImage().getScaledInstance(manzana.getWidth(), manzana.getHeight(), Image.SCALE_SMOOTH)));
        gameOver.add(manzana);

        manzana = new JLabel();
        manzana.setBounds(25, 340, 80, 95);
        manzana.setIcon(new ImageIcon(fruta.getImage().getScaledInstance(manzana.getWidth(), manzana.getHeight(), Image.SCALE_SMOOTH)));
        gameOver.add(manzana);

        /*Componentes para las cajas de texto*/
        respuesta = new JTextField();
        respuesta.setText("=");
        respuesta.setBounds(700, 480, 220, 30);
        respuesta.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        respuesta.setFont(new Font("Arial", Font.BOLD, 18));
        respuesta.setHorizontalAlignment(SwingConstants.CENTER);
        respuesta.addKeyListener(this);


        /*Componentes para los botones*/

        por1 = new JButton("x 1");
        por2 = new JButton("x 2");
        por3 = new JButton("x 3");
        por4 = new JButton("x 4");
        por5 = new JButton("x 5");
        por6 = new JButton("x 6");
        por7 = new JButton("x 7");
        por8 = new JButton("x 8");
        por9 = new JButton("x 9");
        por10 = new JButton("x 10");
        por11 = new JButton("x 11");
        por12 = new JButton("x 12");
        abrir = new JButton();
        cerrar = new JButton();
        iniciar = new JButton();

        JButton lista[] = {por1, por2, por3, por4, por5, por6, por7, por8, por9, por10, por11, por12};

        int y = 25;

        for (int i = 0; i < lista.length; i++) {

            lista[i].setBounds(50, y, 85, 30); //ubicación
            lista[i].setFocusable(false); //que no se en marque al presionar el boton
            lista[i].setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0))); //borde de linea negra
            lista[i].setBackground(Color.white); //color del boton blanco
            lista[i].addActionListener(this); //activamos  los eventos para que los botones hagan alguna funcion.

            panel_operaciones.add(lista[i]); //añadimos cada boton al panel de operaciones.
            y += 45; //incrementamos la seperacion que hay entre un boton y otro

        } //fin ciclo for


        ImageIcon menu = new ImageIcon(getClass().getResource("/Imagenes/menu.png"));
        cerrar.setIcon(menu);
        cerrar.setBounds(15, 15, 25, 25);
        cerrar.setIcon(new ImageIcon(menu.getImage().getScaledInstance(cerrar.getWidth(), cerrar.getHeight(), Image.SCALE_SMOOTH)));
        cerrar.setBackground(Color.white);
        cerrar.setBorder(BorderFactory.createLineBorder(Color.white));
        cerrar.addActionListener(this);
        panel_operaciones.add(cerrar);


        abrir.setBounds(30, 25, 25, 25);
        abrir.setIcon(new ImageIcon(menu.getImage().getScaledInstance(abrir.getWidth(), abrir.getHeight(), Image.SCALE_SMOOTH)));
        abrir.setBackground(Color.white);
        abrir.addActionListener(this);
        abrir.setBorder(BorderFactory.createLineBorder(Color.white));
        panel_fondo.add(abrir);


        iniciar = new JButton("JUGAR OTRA VEZ?");
        iniciar.setBounds(320, 300, 230, 45); //ubicación
        iniciar.setFocusable(false); //que no se en marque al presionar el boton
        iniciar.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0))); //borde de linea negra
        iniciar.setBackground(Color.white); //color del boton blanco
        iniciar.setFont(new Font("Arial", Font.BOLD, 18));
        iniciar.addActionListener(this);
        gameOver.add(iniciar);





        /*Añadiendo todo a la ventaja*/
        gameOver.add(game_over);
        fondo.add(gameOver);
        panel_pregunta.add(pregunta);
        panel_puntaje.add(puntaje);
        fondo.add(panel_puntaje);
        fondo.add(panel_pregunta);
        fondo.add(acertado);
        fondo.add(respuesta);


        fondo.add(tempo);
        fondo.add(plus);

        panel_fondo.add(panel_operaciones);
        panel_fondo.add(fondo);
        panel_fondo.add(multiplo);


        add(panel_fondo);


    }//componentes_graficos

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == iniciar) {
            contador = 10;
            puntuacion = 0;
            puntaje.setText("PUNTAJE: " + puntuacion);
            respuesta.setText("=");
            respuesta.setVisible(true);
            tempo.setForeground(colores[0]);
            gameOver.setVisible(false);
            pregunta.setVisible(true);
            abrir.setVisible(true);
            temporizador.start();

        }

        if (e.getSource() == cerrar) {
            panel_operaciones.setVisible(false);
            abrir.setVisible(true);

        }
        if (e.getSource() == abrir) {
            panel_operaciones.setVisible(true);
            abrir.setVisible(false);

        }

        if (e.getSource() == por1) {
            operacion = 1;

        }
        if (e.getSource() == por2) {
            operacion = 2;

        }
        if (e.getSource() == por3) {
            operacion = 3;

        }
        if (e.getSource() == por4) {
            operacion = 4;

        }
        if (e.getSource() == por5) {
            operacion = 5;

        }
        if (e.getSource() == por6) {
            operacion = 6;

        }
        if (e.getSource() == por7) {
            operacion = 7;

        }
        if (e.getSource() == por8) {
            operacion = 8;

        }
        if (e.getSource() == por9) {
            operacion = 9;

        }
        if (e.getSource() == por10) {
            operacion = 10;

        }
        if (e.getSource() == por11) {
            operacion = 11;

        }
        if (e.getSource() == por12) {
            operacion = 12;

        }
        if (e.getSource() == temporizador) {
            if (estado == true) {
                numero_aleatorio = numeros.numero();
                pregunta.setText(operacion + " x " + numero_aleatorio);
                estado = false;
            } else {

                pregunta.setText(operacion + " x " + numero_aleatorio);

            }
            tempo.setText(contador + "");
            contador--;
            if (contador > 3 && contador < 7) {
                tempo.setForeground(colores[1]);

            }

            if (contador < 3) {
                tempo.setForeground(colores[2]);

            }
            if (contador < 0) {
                contador = 0;
                temporizador.stop();
                i=0;
                //revisamos el numero mayor que se guardo en el array
                for(int j=0;j<puntuacion;j++){

                    if(mayor_puntaje<puntajes[j]){
                        mayor_puntaje=puntajes[j];
                    }

                }

                label1.setText("PUNTAJE MAS ALTO: " + mayor_puntaje);
                label2.setText("PUNTAJE: " + puntuacion);
                respuesta.setVisible(false);
                panel_operaciones.setVisible(false);
                gameOver.setVisible(true);


            }

        }//temporziador

        if (e.getSource() == duracion_plus) {
            if (estado_acertado == true) {
                plus.setVisible(false);
                acertado.setVisible(false);
                respuesta.setEnabled(true);
                respuesta.setText("= ");

                duracion_plus.stop();
            } else {
                acertado.setVisible(false);
                respuesta.setText("= ");
                respuesta.setEnabled(true);
                duracion_plus.stop();

            }

        }
        multiplo.setText("Multiplicación de  " + operacion + " ");

    }//actionPerformed

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent tecla) {
        if (tecla.getSource() == respuesta && tecla.getKeyChar() == KeyEvent.VK_ENTER) {
            String cadena = respuesta.getText(); //leo toda la caja de texto
            cadena = cadena.substring(2, cadena.length()); //como hay un = y espacio antes del numero, lo que hago es extraer solo el numero
            int numero = Integer.parseInt(cadena);//ese numero que esta como texto o cadena lo convierto a numero entero

            int respuesta_operacion = operacion * numero_aleatorio;

            if (respuesta_operacion == numero) {

                estado_acertado = true;
                plus.setVisible(true);
                contador = 10;
                tempo.setForeground(colores[0]);
                estado = true;
                puntuacion++;
                puntajes[i]=puntuacion;
                i++;
                respuesta.setEnabled(false);
                ImageIcon correcto = new ImageIcon(getClass().getResource("/Imagenes/bien.png"));
                acertado.setIcon(new ImageIcon(correcto.getImage().getScaledInstance(acertado.getWidth(), acertado.getHeight(), Image.SCALE_SMOOTH)));
                acertado.setVisible(true);
                duracion_plus.start();

            } else {
                estado_acertado = false;
                respuesta.setEnabled(false);
                ImageIcon incorrecto = new ImageIcon(getClass().getResource("/Imagenes/mal.png"));
                acertado.setIcon(new ImageIcon(incorrecto.getImage().getScaledInstance(acertado.getWidth(), acertado.getHeight(), Image.SCALE_SMOOTH)));
                acertado.setVisible(true);
                duracion_plus.start();


            }

            puntaje.setText("PUNTAJE : " + puntuacion);

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}// clase interfaz


class operaciones {


    int numero() {

        int valor = ThreadLocalRandom.current().nextInt(0, 13);

        return valor;
    }


}// class operaciones