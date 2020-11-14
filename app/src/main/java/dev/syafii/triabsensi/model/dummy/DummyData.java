package dev.syafii.triabsensi.model.dummy;

import java.util.ArrayList;

public class DummyData {
    public static String[][] data = new String[][]{
            {
                    "Muhamad Syafii",
                    "PT SECODE",
                    "123456",
                    "Masuk",
                    "30 June 2020"
            },
            {
                    "Muhamad Syafii",
                    "PT SECODE",
                    "123456",
                    "Keluar",
                    "30 June 2020"
            },
            {
                    "Muhamad Syafii",
                    "PT SECODE",
                    "123456",
                    "Masuk",
                    "28 June 2020"
            },
            {
                    "Muhamad Syafii",
                    "PT SECODE",
                    "123456",
                    "Keluar",
                    "28 June 2020"
            },
            {
                    "Muhamad Syafii",
                    "PT SECODE",
                    "123456",
                    "Masuk",
                    "27 June 2020"
            },
            {
                    "Muhamad Syafii",
                    "PT SECODE",
                    "123456",
                    "Keluar",
                    "27 June 2020"
            },
            {
                    "Muhamad Syafii",
                    "PT SECODE",
                    "123456",
                    "Masuk",
                    "26 June 2020"
            },
            {
                    "Muhamad Syafii",
                    "PT SECODE",
                    "123456",
                    "Keluar",
                    "26 June 2020"
            },
            {
                    "Muhamad Syafii",
                    "PT SECODE",
                    "123456",
                    "Masuk",
                    "25 June 2020"
            },
            {
                    "Muhamad Syafii",
                    "PT SECODE",
                    "123456",
                    "Keluar",
                    "25 June 2020"
            },
            {
                    "Muhamad Syafii",
                    "PT SECODE",
                    "123456",
                    "Masuk",
                    "24 June 2020"
            },
            {
                    "Muhamad Syafii",
                    "PT SECODE",
                    "123456",
                    "Keluar",
                    "24 June 2020"
            },
    };

    public static ArrayList<DummyModel> getData(){
        ArrayList<DummyModel> list = new ArrayList<>();
        for (String[] item : data){
            DummyModel model = new DummyModel();
            model.setName(item[0]);
            model.setFactory(item[1]);
            model.setNik(item[2]);
            model.setStatus(item[3]);
            model.setDate(item[4]);
            list.add(model);
        }
        return list;
    }
}
