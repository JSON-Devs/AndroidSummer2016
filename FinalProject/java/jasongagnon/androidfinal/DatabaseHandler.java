package jasongagnon.androidfinal;
import java.util.ArrayList;
import java.util.List;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "leaderboardHandler";

	// Accounts table name
	private static final String TABLE_LEADERS = "leaderboards";

	// Accounts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_TIME = "time";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_LEADERS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_TIME + " TEXT" + ")";
		db.execSQL(CREATE_ACCOUNTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEADERS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new Account
	void addLeader(Leader leader) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, leader.getName()); // Account Name
		values.put(KEY_TIME, leader.getTime()); // Account Phone

		// Inserting Row
		db.insert(TABLE_LEADERS, null, values);
		db.close(); // Closing database connection
	}

	// Getting All Accounts
	public List<Leader> getAllLeaders() {
		List<Leader> leaderList = new ArrayList<>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_LEADERS + " ORDER BY " + KEY_TIME  + " ASC";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Leader leader = new Leader();
				leader.setID(Integer.parseInt(cursor.getString(0)));
				leader.setName(cursor.getString(1));
				leader.setTime(cursor.getString(2));
				// Adding Account to list
				leaderList.add(leader);
			} while (cursor.moveToNext());
		}

		// return Account list
		return leaderList;
	}

}
