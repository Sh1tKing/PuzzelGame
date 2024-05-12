package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener {
    int [][]data=new int[4][4];
    int x=0,y=0;
    //游戏主界面
    public GameJFrame(){
        initJFrame();
        initJMenuBar();
        initData();
        initImage();
        this.setVisible(true);
    }

    private void initData() {
        int []tempdata={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        for (int i=0;i<=15;i++){
            Random r=new Random();
            int index=r.nextInt(15);
            int temp=tempdata[index];
            tempdata[index]=tempdata[i];
            tempdata[i]=temp;
        }
        for(int i=0;i<tempdata.length;i++){
            data[i/4][i%4]=tempdata[i];
        }
    }

    private void initImage() {//初始化图片
        this.getContentPane().removeAll();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++) {
                ImageIcon icon = new ImageIcon("image\\girl\\girl1\\" + data[i][j] + ".jpg");
                if(data[i][j]==0){
                    x=i;
                    y=j;
                }
                JLabel jLabel = new JLabel(icon);
                jLabel.setBounds(j*105+80, i*105+100, 105, 105);
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //this.add(jLabel);
                this.getContentPane().add(jLabel);
            }
        }
        ImageIcon icon=new ImageIcon("image\\background1.png");
        JLabel jLabel=new JLabel(icon);
        jLabel.setBounds(0,0,700,700);
        this.getContentPane().add(jLabel);

        this.getContentPane().repaint();

    }
    private void initJMenuBar() {
        //初始化菜单
        JMenuBar jMenuBar=new JMenuBar();
        //菜单对象
        JMenu functionJMenu=new JMenu("功能");
        JMenu aboutJMenu=new JMenu("关于作者");
        //对象条目
        JMenuItem restartItem=new JMenuItem("重新开始");
        JMenuItem reloginItem=new JMenuItem("重新登录");
        JMenuItem terminalItem=new JMenuItem("关闭游戏登录");
        JMenuItem accountItem=new JMenuItem("github地址");

        functionJMenu.add(restartItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(terminalItem);
        aboutJMenu.add(accountItem);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603,680);
        this.setTitle("拼图小游戏V1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==38){
            if(x+1<=3 && x+1>=0) {
                data[x][y]=data[x+1][y];
                data[x+1][y]=0;
                initImage();
            }
        }else if(code==40){
            if(x-1<=3 && x-1>=0) {
                data[x][y]=data[x-1][y];
                data[x-1][y]=0;
                initImage();
            }
        }else if(code==37){
            if(y+1<=3 && y+1>=0) {
                data[x][y]=data[x][y+1];
                data[x][y+1]=0;
                initImage();
            }
        }else if(code==39){
            if(y-1<=3 && y-1>=0) {
                data[x][y]=data[x][y-1];
                data[x][y-1]=0;
                initImage();
            }
        }
    }
}
