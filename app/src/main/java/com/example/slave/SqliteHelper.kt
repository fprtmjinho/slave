package com.example.slave

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelper(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    //onCreate(), onUpgrade() 두가지 메소드를 오버라이드 받아 줍시다.

    //데이터베이스가 만들어 지지않은 상태에서만 작동합니다. 이미 만들어져 있는 상태라면 실행되지 않습니다.
    override fun onCreate(db: SQLiteDatabase?) {
        //테이블을 생성할 쿼리를 작성하여 줍시다.
        val create = "create table numberBook (id integer primary key, name text, number text)"
        //실행시켜 줍니다.
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    //insert 메소드
    fun insertNumberBook(numberBook:NumberBook){
        val values = ContentValues()
        //넘겨줄 컬럼의 매개변수 지정
        values.put("id",numberBook.id)
        values.put("name",numberBook.name)
        values.put("number",numberBook.number)
        //쓰기나 수정이 가능한 데이터베이스 변수
        val wd = writableDatabase
        wd.insert("numberBook",null,values)
        //사용이 끝나면 반드시 close()를 사용하여 메모리누수 가 되지않도록 합시다.
        wd.close()
    }


    //select 메소드
    fun selectNumberBook():MutableList<NumberBook>{
        val list = mutableListOf<NumberBook>()
        //전체조회
        val selectAll = "select * from numberBook"
        //읽기전용 데이터베이스 변수
        val rd = readableDatabase
        //데이터를 받아 줍니다.
        val cursor = rd.rawQuery(selectAll,null)

        //반복문을 사용하여 list 에 데이터를 넘겨 줍시다.
        while(cursor.moveToNext()){
            val id = cursor.getLong(cursor.getColumnIndex("id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val number = cursor.getString(cursor.getColumnIndex("number"))

            list.add(NumberBook(id,name,number))
        }
        cursor.close()
        rd.close()

        return list
    }

    //update 메소드
    fun updateNumberBook(numberBook:NumberBook){
        val values = ContentValues()

        values.put("name",numberBook.name)
        values.put("number",numberBook.number)

        val wd = writableDatabase
        wd.update("memo",values,"id=${numberBook.id}",null)
        wd.close()

    }

    //delete 메소드
    fun deleteNumberBook(numberBook:NumberBook){
        val delete = "delete from numberBook where id = ${numberBook.id}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()

    }

}