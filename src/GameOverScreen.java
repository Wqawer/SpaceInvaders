import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GameOverScreen extends Panel {
    public GameOverScreen(MainWindow owner,long score){
        boolean isTheBest =false;
        ArrayList<String> scores = new ArrayList<String>();
        Charset charset = Charset.forName("US-ASCII");
        Path path = Paths.get("./score.txt");
        File file = new File(path.toString());
        if(file.exists()){
            try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
                String line = null;
                while ((line = reader.readLine()) != null&&scores.size()<5) {
                    scores.add(line);
                    System.out.println(line);
                }

            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
        else{
            try{
                file.createNewFile();
            }
            catch (IOException ex){

            }
        }
        int index= 0;
        String tmp=Long.toString(score);
        if(scores.isEmpty()){
            isTheBest = true;
            scores.add(tmp);
        }
        else{
            while(index<scores.size()){
                if(Long.parseLong(scores.get(index))<Long.parseLong(tmp)){
                    String copy = scores.get(index);
                    scores.set(index,tmp);
                    tmp=copy;
                    if(index==0)
                        isTheBest = true;
                }
                index++;
            }
            if(scores.size()<5 && score == Long.parseLong(tmp)){
                scores.add(tmp);
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(path, charset)) {

            for(String str: scores) {
                writer.write(str + System.lineSeparator());
            }
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
        setBounds(0,0,500,500);
        setBackground(Color.BLACK);
        setVisible(true);
        if(isTheBest){
            Label best = new Label("New best score!",Label.CENTER);
            best.setForeground(Color.WHITE);
            best.setBounds(0,50,500,50);
            best.setFont(new Font("Lucida",Font.PLAIN,24));
            add(best);
        }
        Label label = new Label("score: "+score,Label.CENTER);
        label.setForeground(Color.WHITE);
        label.setBounds(0,100,500,50);
        label.setFont(new Font("Lucida",Font.PLAIN,24));
        add(label);
        for(int i =0; i<scores.size();i++){
            int place = i+1;
            Label l = new Label(place+". "+scores.get(i), Label.CENTER);
            l.setBounds(0,150+i*20,500,20);
            l.setForeground(Color.WHITE);
            add(l);
        }
        Button newGameButton = new Button("new game");
        newGameButton.setBounds(200,300,100,50);
        newGameButton.addActionListener((e)-> owner.startGame(this));
        add(newGameButton);
    }
}
