package com.example.p_pt09_2072009.thread;

import com.example.p_pt09_2072009.Util.MyConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class threadsimple extends Thread {
    private callbackinterface oncomplete;

    public threadsimple(callbackinterface oncomplete) {
        this.oncomplete=oncomplete;
    }

    @Override
    public void run() {
        JasperPrint jp;
        Connection conn = MyConnection.getConnection();
        Map param = new HashMap();
        try {
            jp = JasperFillManager.fillReport("report/Menu.jasper", param, conn);
            JasperViewer viewer = new JasperViewer(jp, false);
            viewer.setTitle("Menu Report");
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        if(oncomplete != null){
            oncomplete.oncomplete();
        }
    }
}
