package com;

import android.database.Cursor;

import com.kamus.ws.kamus.DBAdapter;
import com.kamus.ws.kamus.R;

public class DB {
    DBAdapter mDB;
    protected Cursor cursor;


    public static String [] getData(int id) {
        if (id == R.id.action_semua) {
            return getsemua();
        } else if (id == R.id.action_hardware) {
            return gethardware();
        } else if (id == R.id.action_jaringan) {
            return getjaringan();
        } else if (id == R.id.action_software) {
            return getsoftware();
        } else if (id == R.id.action_pemrograman) {
            return getpemrograman();
        }
        return new String[0];
    }

    public static String[] getsemua(){

        String[] source = new String[] {
                "a11",
                "abend (Abnormal end)",
                "ABI (Application Binary Interface)",
                "AbiWord",
                "Abort",
                "Access",
                "Accessibillity",
                "Access Method",
                "Access Time",
                "Accessories",
                "Accumulator",
                "Acknowledge",
                "Accoustic Coupler",
        };
        return source;

    }

    public static String[] gethardware(){
        String[] source = new String[] {
                "a1",
                "abend (Abnormal end)",
                "ABI (Application Binary Interface)",
                "AbiWord",
                "Abort",
                "Access",
                "Accessibillity",
                "Access Method",
                "Access Time",
                "Accessories",
                "Accumulator",
                "Acknowledge",
                "Accoustic Coupler",
        };
        return source;

        }

    public static String[] getjaringan(){
        String[] source = new String[] {
                "a2",
                "abend (Abnormal end)",
                "ABI (Application Binary Interface)",
                "AbiWord",
                "Abort",
                "Access",
                "Accessibillity",
                "Access Method",
                "Access Time",
                "Accessories",
                "Accumulator",
                "Acknowledge",
                "Accoustic Coupler",
                "ACL (Access Control Unit)",
                "ACPI (Advanced Configuration Power Interface)",
                "Active Task Button",
                "Active",
                "ActiveX",
        };
        return source;
    }

    public static String[] getsoftware(){
        String[] source = new String[] {
                "a3",
                "abend (Abnormal end)",
                "ABI (Application Binary Interface)",
                "AbiWord",
                "Abort",
                "Access",
                "Accessibillity",
                "Access Method",
                "Access Time",
                "Accessories",
                "Accumulator"
        };
        return source;
    }

    public static String[] getpemrograman(){
        String[] source = new String[] {
                "a4",
                "abendy",
                "ABI (Application Binary Interface)",
                "AbiWord",
                "Aborting",
                "Accessing",
                "Accessibillity",
                "Access Method",
                "Access Time",
                "Accessories",
                "Accumulator",
                "Acknowledge",
                "Accoustic Coupler",
                "ACL (Access Control Unit)",
                "ACPI (Advanced Configuration Power Interface)",
                "Active Task Button",
                "Active",
                "ActiveX",
                "Adapter",
                "ADC (Analog/Digital Converter)",
                "Add-in",
        };
        return source;
     }
}
