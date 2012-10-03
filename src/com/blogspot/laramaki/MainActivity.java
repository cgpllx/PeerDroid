package com.blogspot.laramaki;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	Peer2Peer p2p;
	ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p2p = new Peer2Peer(this);
        listView = (ListView) findViewById(R.id.list_peers);
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this, android.R.layout.simple_expandable_list_item_1, (Object[]) p2p.getListaDePeers().toArray());
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void envia(View v) {
    	p2p.enviaMensagem();
    	((TextView) findViewById(R.id.tv_meu_ip)).setText(p2p.getMeuEnderecoIP());
    	ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this, android.R.layout.simple_expandable_list_item_1, (Object[]) p2p.getListaDePeers().toArray());
        listView.setAdapter(adapter);
    }
}
