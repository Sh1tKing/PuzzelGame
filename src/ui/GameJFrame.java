package ui;

import javax.swing.*;

public class GameJFrame extends JFrame {
    //游戏主界面
    public GameJFrame(){
        initJFrame();
        initJMenuBar();
        this.setVisible(true);
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
    }

}
