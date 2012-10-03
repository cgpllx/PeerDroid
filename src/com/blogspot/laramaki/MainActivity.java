package com.blogspot.laramaki;

import com.blogspot.laramaki.Peer2Peer.ListenerDeNovosObjetosRecebidos;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements ListenerDeNovosObjetosRecebidos {

	Peer2Peer	p2p;
	ListView	listView;
	EditText	edtText;
	ImageView	ivPic;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		p2p = new Peer2Peer(this);
		p2p.setListenerDeNovosObjetosRecebidos(this);
		listView = (ListView) findViewById(R.id.list_peers);
		ivPic = (ImageView) findViewById(R.id.iv_pic);
		edtText = (EditText) findViewById(R.id.edt_text);
		ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this, android.R.layout.simple_expandable_list_item_1, (Object[]) p2p.getListaDePeers().toArray());
		listView.setAdapter(adapter);
	}

	public void envia(View v) {
//		p2p.enviaMensagemDeTexto(edtText.getText().toString());
		p2p.enviaImagem(getResources().getDrawable(R.drawable.foto_paciente));
		((TextView) findViewById(R.id.tv_meu_ip)).setText(p2p.getMeuEnderecoIP());
		ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this, android.R.layout.simple_expandable_list_item_1, (Object[]) p2p.getListaDePeers().toArray());
		listView.setAdapter(adapter);
	}

	@Override
	public void objetoRecebido(String endereco, int tipo, Object objeto) {
		switch (tipo) {
			case Peer2Peer.TIPO_OBJETO_IMAGEM:
				final Drawable drawable = (Drawable) objeto;
				android.util.Log.i("Peer2Peer", "Imagem Recebida " + drawable );
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						ivPic.setImageDrawable(drawable);
						android.widget.Toast.makeText(MainActivity.this, "Imagem Recebida!", android.widget.Toast.LENGTH_LONG).show();
						ivPic.requestLayout();
					}
				});
				break;
			case Peer2Peer.TIPO_OBJETO_TEXTO:
				break;
			default:
				break;
		}
	}
}
