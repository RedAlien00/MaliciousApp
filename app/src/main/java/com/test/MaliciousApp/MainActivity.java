package com.test.MaliciousApp;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button BTN_uriSet;
    EditText ET_uri;

    Button BTN_insert;
    EditText ET_insert_column;
    EditText ET_insert_value;

    Button BTN_query;
    EditText ET_query_projection;
    EditText ET_query_selection;
    EditText ET_query_selectionArgs;
    EditText ET_query_sortOrder;

    Button BTN_update;
    EditText ET_update_column;
    EditText ET_update_value;
    EditText ET_update_selection;
    EditText ET_update_selectionArgs;

    Button BTN_delete;
    EditText ET_delete_selection;
    EditText ET_delete_selectionArgs;

    protected static final String TAG = "Malicious";
    protected ContentResolver contentResolver;
    protected Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BTN_uriSet = findViewById(R.id.BTN_uriSet);
        ET_uri = findViewById(R.id.ET_uri);

        BTN_insert = findViewById(R.id.BTN_insert);
        ET_insert_column = findViewById(R.id.ET_insert_column);
        ET_insert_value = findViewById(R.id.ET_insert_value);

        BTN_query = findViewById(R.id.BTN_query);
        ET_query_projection = findViewById(R.id.ET_query_projection);
        ET_query_selection = findViewById(R.id.ET_query_selection);
        ET_query_selectionArgs = findViewById(R.id.ET_query_selectionArgs);
        ET_query_sortOrder = findViewById(R.id.ET_query_sortOrder);

        BTN_update = findViewById(R.id.BTN_update);
        ET_update_column = findViewById(R.id.ET_update_column);
        ET_update_value = findViewById(R.id.ET_update_value);
        ET_update_selection = findViewById(R.id.ET_update_selecton);
        ET_update_selectionArgs = findViewById(R.id.ET_update_selectionArgs);

        BTN_delete = findViewById(R.id.BTN_delete);
        ET_delete_selection = findViewById(R.id.ET_delete_selection);
        ET_delete_selectionArgs = findViewById(R.id.ET_delete_selectionArgs);

        contentResolver = getContentResolver();

        initBTN_uriset_lisnter();
        initBTNs_listener();
    }

    // 공백, 띄어쓰기일 떄 토스트 출력
    // URI 생성이 실패했다면 올바르지 않은 uri 토스트 출력
    private void initBTN_uriset_lisnter() {
        BTN_uriSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = ET_uri.getText().toString().trim();

                if (input.isEmpty() || input.equals(" ")) {
                    uri = null;
                    MyUtil.myToast(MainActivity.this, "공백 입력 불가");
                    return;
                }
                uri = Uri.parse(input);
                MyUtil.myToast(MainActivity.this, "URI 설정 완료");
            }
        });
    }

    private void initBTNs_listener() {
        BTN_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri == null){
                    MyUtil.myToast(MainActivity.this,"URI부터 입력해주세요");
                    return;
                }
                String column = ET_insert_column.getText().toString().trim();
                String value = ET_insert_value.getText().toString().trim();

                Insert insert = new Insert(MainActivity.this, contentResolver, uri, column, value);
                insert.doInsert();
            }
        });
        BTN_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri == null){
                    MyUtil.myToast(MainActivity.this,"URI부터 입력해주세요");
                    return;
                }

                String projection = ET_query_projection.getText().toString().trim();
                String selection = ET_query_selection.getText().toString().trim();
                String selectionArgs = ET_query_selectionArgs.getText().toString().trim();
                String sortOrder = ET_query_sortOrder.getText().toString().trim();

                Query query = new Query(MainActivity.this, contentResolver, uri, projection,
                        selection, selectionArgs, sortOrder );
                query.doQuery();
            }
        });
        BTN_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri == null){
                    MyUtil.myToast(MainActivity.this,"URI부터 입력해주세요");
                    return;
                }
                String column = ET_update_column.getText().toString().trim();
                String value = ET_update_value.getText().toString().trim();
                String selection = ET_update_selection.getText().toString().trim();
                String selectionArgs = ET_update_selectionArgs.getText().toString().trim();

                Update update = new Update(MainActivity.this, contentResolver, uri, column, value,
                        selection, selectionArgs);
                update.doUpdate();
            }
        });
        BTN_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uri == null){
                    MyUtil.myToast(MainActivity.this,"URI부터 입력해주세요");
                    return;
                }
                String selection = ET_delete_selection.getText().toString().trim();
                String selectionArgs = ET_delete_selectionArgs.getText().toString().trim();

                Delete delete = new Delete(MainActivity.this, contentResolver, uri, selection, selectionArgs);
                delete.doDelete();
            }
        });
    }
}