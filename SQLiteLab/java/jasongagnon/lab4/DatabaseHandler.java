package jasongagnon.lab4;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 3;

	// Database Name
	private static final String DATABASE_NAME = "ordersManager";

	// Accounts table name
	private static final String TABLE_ORDERS = "orders";

	// Accounts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_FNAME = "fName";
	private static final String KEY_LNAME = "lName";
	private static final String KEY_TYPE = "type";
	private static final String KEY_NOOFBARS = "noOfBars";
	private static final String KEY_EXP = "isExpediated";
	private static final String KEY_PRICE = "price";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_FNAME + " TEXT,"
				+ KEY_LNAME + " TEXT," + KEY_TYPE + " TEXT," + KEY_NOOFBARS + " INTEGER,"
				+ KEY_EXP + " INTEGER," + KEY_PRICE + " REAL" + ")";
		db.execSQL(CREATE_ORDERS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new Account
	void addOrder(Order order) {
		SQLiteDatabase db = this.getWritableDatabase();

		int exp;

		//Convert boolean to integer
		if(order.isShippingExpedited()){
			exp = 1;
		}
		else{
			exp = 0;
		}

		ContentValues values = new ContentValues();
		//values.put(KEY_NAME, order.getName()); // Account Name
		//values.put(KEY_TYPE, order.getType()); // Account Phone
		values.put(KEY_FNAME, order.getfName());
		values.put(KEY_LNAME, order.getlName());
		values.put(KEY_TYPE, order.getType());
		values.put(KEY_NOOFBARS, order.getNoOfBars());
		values.put(KEY_EXP, exp);
		values.put(KEY_PRICE, order.getPrice());

		// Inserting Row
		db.insert(TABLE_ORDERS, null, values);
		db.close(); // Closing database connection
	}

	// Getting single Account
	Order idSearch(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_ORDERS, new String[] { KEY_ID,
				KEY_FNAME, KEY_LNAME, KEY_TYPE, KEY_NOOFBARS, KEY_EXP, KEY_PRICE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Order order = new Order();
		order.setId(Integer.valueOf(cursor.getString(0)));
		order.setfName(cursor.getString(1));
		order.setlName(cursor.getString(2));
		order.setType(cursor.getString(3));
		order.setNoOfBars(Integer.valueOf(cursor.getString(4)));
		order.setPrice(Float.valueOf(cursor.getString(6)));
		if(Integer.valueOf(cursor.getString(5)) == 1) {
			order.setShippingExpedited(true);
		}
		else{
			order.setShippingExpedited(false);
		}
		return order;
	}

	// Getting All Accounts
	public List<Order> getAllOrders() {
		List<Order> orderList = new ArrayList<Order>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ORDERS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Order order = new Order();
				order.setId(Integer.valueOf(cursor.getString(0)));
				order.setfName(cursor.getString(1));
				order.setlName(cursor.getString(2));
				order.setType(cursor.getString(3));
				order.setNoOfBars(Integer.valueOf(cursor.getString(4)));
				order.setPrice(Float.valueOf(cursor.getString(6)));
				if(Integer.valueOf(cursor.getString(5)) == 1) {
					order.setShippingExpedited(true);
				}
				else{
					order.setShippingExpedited(false);
				}
				orderList.add(order);
			} while (cursor.moveToNext());
		}

		// return Account list
		return orderList;
	}

	public List<Order> priceSearch(float priceSearch) {
		List<Order> orderList = new ArrayList<Order>();
		// Select All Query
		//String selectQuery = "SELECT  * FROM " + TABLE_ORDERS + " WHERE " + KEY_PRICE + " > " + priceSearch;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM orders WHERE price > ?",new String[] {String.valueOf(priceSearch)});

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Order order = new Order();
				order.setId(Integer.valueOf(cursor.getString(0)));
				order.setfName(cursor.getString(1));
				order.setlName(cursor.getString(2));
				order.setType(cursor.getString(3));
				order.setNoOfBars(Integer.valueOf(cursor.getString(4)));
				order.setPrice(Float.valueOf(cursor.getString(6)));
				if(Integer.valueOf(cursor.getString(5)) == 1) {
					order.setShippingExpedited(true);
				}
				else{
					order.setShippingExpedited(false);
				}
				orderList.add(order);
			} while (cursor.moveToNext());
		}

		// return Account list
		return orderList;
	}

	// Updating single Account
	public int updateAccount(Order order) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		//values.put(KEY_NAME, order.getName());
		values.put(KEY_TYPE, order.getType());

		// updating row
		return db.update(TABLE_ORDERS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(order.getId()) });
	}

	// Deleting single Account
	public void deleteAccount(Order order) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_ORDERS, KEY_ID + " = ?",
				new String[] { String.valueOf(order.getId()) });
		db.close();
	}


	// Getting Accounts Count
	public int getOrdersCount() {
		String countQuery = "SELECT  * FROM " + TABLE_ORDERS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int count = cursor.getCount();
		cursor.close();
		// return count
		return count;
	}

}
