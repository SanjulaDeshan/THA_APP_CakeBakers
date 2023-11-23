package com.tha.tha_app_204171h.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.tha.tha_app_204171h.data.entity.ItemInfo;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ItemInfoDao_Impl implements ItemInfoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ItemInfo> __insertionAdapterOfItemInfo;

  private final EntityDeletionOrUpdateAdapter<ItemInfo> __deletionAdapterOfItemInfo;

  private final EntityDeletionOrUpdateAdapter<ItemInfo> __updateAdapterOfItemInfo;

  public ItemInfoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfItemInfo = new EntityInsertionAdapter<ItemInfo>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `Item_Infor` (`itemId`,`name`,`description`,`price`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final ItemInfo entity) {
        statement.bindLong(1, entity.itemId);
        if (entity.name == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.name);
        }
        if (entity.description == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.description);
        }
        statement.bindDouble(4, entity.price);
      }
    };
    this.__deletionAdapterOfItemInfo = new EntityDeletionOrUpdateAdapter<ItemInfo>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Item_Infor` WHERE `itemId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final ItemInfo entity) {
        statement.bindLong(1, entity.itemId);
      }
    };
    this.__updateAdapterOfItemInfo = new EntityDeletionOrUpdateAdapter<ItemInfo>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Item_Infor` SET `itemId` = ?,`name` = ?,`description` = ?,`price` = ? WHERE `itemId` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement, final ItemInfo entity) {
        statement.bindLong(1, entity.itemId);
        if (entity.name == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.name);
        }
        if (entity.description == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.description);
        }
        statement.bindDouble(4, entity.price);
        statement.bindLong(5, entity.itemId);
      }
    };
  }

  @Override
  public void addItem(final ItemInfo itemInfo) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfItemInfo.insert(itemInfo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final ItemInfo itemInfo) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfItemInfo.handle(itemInfo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateItem(final ItemInfo itemInfo) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfItemInfo.handle(itemInfo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<ItemInfo>> getAllItems() {
    final String _sql = "Select * from Item_Infor";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"Item_Infor"}, false, new Callable<List<ItemInfo>>() {
      @Override
      @Nullable
      public List<ItemInfo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfItemId = CursorUtil.getColumnIndexOrThrow(_cursor, "itemId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final List<ItemInfo> _result = new ArrayList<ItemInfo>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ItemInfo _item;
            _item = new ItemInfo();
            _item.itemId = _cursor.getInt(_cursorIndexOfItemId);
            if (_cursor.isNull(_cursorIndexOfName)) {
              _item.name = null;
            } else {
              _item.name = _cursor.getString(_cursorIndexOfName);
            }
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _item.description = null;
            } else {
              _item.description = _cursor.getString(_cursorIndexOfDescription);
            }
            _item.price = _cursor.getDouble(_cursorIndexOfPrice);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
