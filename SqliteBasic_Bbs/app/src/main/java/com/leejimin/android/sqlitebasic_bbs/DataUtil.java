package com.leejimin.android.sqlitebasic_bbs;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Jasmine on 2016-10-11.
 */

//insert
//select


public class DataUtil {


    public static final String DB_NAME = "sqlite.db";

    public static void insert(Context context, BbsData data) {


        SQLiteDatabase db = null;
        try {
            //1. db 를 연결한다
            db = openDatabase(context,DB_NAME);
            //2. 쿼리를 만든다
            String query = "insert into bbs2(name,title,contents) values("
                    + "'"+data.name+"','"+data.title+"','"+data.contents+"')";


            //3. 쿼리를 실행한다
            db.execSQL(query);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (db != null) db.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }


    }



    public static BbsData select(Context context, int bbsno) {

        BbsData data = new BbsData();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {

            db = openDatabase(context, DB_NAME);

            String query = "select * from bbs2 where no=" + bbsno;
            cursor = db.rawQuery(query, null);

            if (cursor.moveToNext()) {
                int idx = cursor.getColumnIndex("no");
                data.no = cursor.getInt(idx);

                idx = cursor.getColumnIndex("title");
                data.title = cursor.getString(idx);

                idx = cursor.getColumnIndex("name");
                data.name = cursor.getString(idx);

                idx = cursor.getColumnIndex("contents");
                data.contents = cursor.getString(idx);

                idx = cursor.getColumnIndex("ndate");
                data.ndate = cursor.getString(idx);


            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (db != null) db.close();
                if (cursor != null) cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return data;

    }



    //db에서 데이터의 총 개수를 가져오는 함수
    public static int selectCount(Context context) {

        int count = 0;
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {

            db = openDatabase(context, DB_NAME);

            String query = "select count(*) from bbs2";
            cursor = db.rawQuery(query, null);

            if (cursor.moveToNext()) {

                count = cursor.getInt(0);

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (db != null) db.close();
                if (cursor != null) cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return count;

    }


    public static int selectCountByWord(Context context) {

        int count = 0;
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {

            db = openDatabase(context, DB_NAME);

            String query = "select count(*) from bbs2";
            cursor = db.rawQuery(query, null);

            if (cursor.moveToNext()) {

                count = cursor.getInt(0);

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (db != null) db.close();
                if (cursor != null) cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return count;

    }




    public static ArrayList<BbsData> selectAllbyWord(Context context, int count, int skip, String word) {

        ArrayList<BbsData> datas = new ArrayList<>();

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {

            db = openDatabase(context, DB_NAME);

            //조건절을 만든다
            String where ="";
            if(!"".equals(word))
                where = "where title like '%"+word+"%'";

            String query = "select no,title from bbs2 order by no desc limit "+count;

            cursor = db.rawQuery(query, null);

            while (cursor.moveToNext()) {
                BbsData data = new BbsData();
                int idx = cursor.getColumnIndex("no");
                data.no = cursor.getInt(idx);

                idx = cursor.getColumnIndex("title");
                data.title = cursor.getString(idx);
                datas.add(data);

            }


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (db != null) db.close();
                if (cursor != null) cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return datas;
    }

    public static ArrayList<BbsData> selectAll(Context context, int count, int skip) {

        return selectAllbyWord(context, count,skip, "");
    }


    public static void update(Context context, BbsData data) {


        SQLiteDatabase db = null;

        try {

            db = openDatabase(context, DB_NAME);

            String query = "update bbs3 set "
                    + " name='"+data.name+"'"
                    + " ,title='"+data.title+"'"
                    + " ,contents='"+data.contents+"'"
                    + " ,ndate=CURRENT_TIMESTAMP"
                    + " where no="+data.no;

            db.execSQL(query);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (db != null) db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }

    public static void delete(int bbsno) {


    }


    //데이터베이스를 정의


    public static SQLiteDatabase openDatabase(Context context, String dbFileName) {
        return SQLiteDatabase.openDatabase(getFullpath(context, dbFileName), null, 0); // 0: 쓰기가능 1: read only
    }


    public static String getFullpath(Context context, String fileName) {
        // internal 디렉토리중 files 디렉토리의 경로를 가져온다
        return context.getFilesDir().getAbsolutePath() + File.separator + fileName;
    }


    public static void assetToDisk(Context context, String fileName) {
        InputStream is = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            // 외부에서 작성된 sqlite db파일 사용하기
            // 1. assets 에 담아둔 파일을 internal 혹은 external
            //    공간으로 복사하기 위해 읽어온다
            AssetManager manager = context.getAssets();
            // assets 에 파일이 없으면 exception 이 발생하여 아래 로직이 실행되지 않는다
            is = manager.open(fileName);
            bis = new BufferedInputStream(is);
            // 2. 저장할 위치에 파일이 없으면 생성한다
            String targetFile = getFullpath(context, fileName);
            File file = new File(targetFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            // 3. outputStream 을 생성해서 파일내용을 쓴다
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            // 읽어올 데이터를 담아줄 변수
            int read = -1; // 모두 읽어오면 -1이 리턴된다
            // 한번에 읽을 버퍼의 크기를 지정
            byte buffer[] = new byte[1024];
            // 더 이상 읽어올 데이터가 없을때까지 buffer 단위로 읽어서 쓴다
            while ((read = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, read);
            }
            // 남아 있는 데이터를 buffer에서 써준다
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 작업이 완료되면 모든 stream을 닫아준다
                if (bos != null) bos.close();
                if (fos != null) fos.close();
                if (bis != null) bis.close();
                if (is != null) is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
