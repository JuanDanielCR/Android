package com.mejorandola.android.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mejorandola.android.R;
import com.mejorandola.android.models.Tweet;
import com.mejorandola.android.utils.BitmapManager;
import com.mejorandola.android.utils.DateUtils;

import java.util.ArrayList;

/**
 * Created by Juan Daniel on 04/05/2015.
 */
public class TweetAdapter extends ArrayAdapter<Tweet> {

    private Context context;
    private ArrayList<Tweet> tweets;

    //Inicializo los parametros
    /*textViewResourceCode.- e rescruso de codigo osea el xml al que le mandare los datos
    * context: de la app
    * arraylist: los datos
    *
    * */
    public TweetAdapter(Context context, int textViewResourceId, ArrayList<Tweet> tweets) {
        super(context, textViewResourceId,tweets);
        this.context=context;
        //Ej: Al llamar este constructor le guardare todos los tweets que la actividad previamente ya se habia descargado
        this.tweets=tweets;
    }

    //Creo la clase viewHolder que contendra las vistas a llenar
    static class ViewHolder{
        public ImageView avatar;
        public TextView name;
        public TextView screenName;
        public TextView text;
        public TextView createdAt;
    }
    //Sobreescribo getView para poder pintar la UI
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Veo si la vista ya fue pintada(en este caso la columna)
        /**
         * Position: me da la posicion dentro de la vista
         * ConvertView: es lo que se pinta en si
         * ViewGroup: Es el padre que envuelve a la vista que pinte
         * **/

        //PINTAR
         if(convertView==null){
            //Aqui inflamos la vista row_tweet xml y la mandamos al contexto que nos enviaron es decir la juntamos con la clase
            convertView= LayoutInflater.from(context).inflate(R.layout.row_tweet,parent,false);
             //Accedo a las vistas que cree y las inflo con su id
            ViewHolder vista=new ViewHolder();
             vista.avatar=(ImageView)convertView.findViewById(R.id.avatar);
             vista.name=(TextView) convertView.findViewById(R.id.name);
             vista.screenName=(TextView) convertView.findViewById(R.id.screen_name);
             vista.text=(TextView) convertView.findViewById(R.id.text);
             vista.createdAt=(TextView) convertView.findViewById(R.id.created_at);
             //Envio el tag
             convertView.setTag(vista);
         }
        //ASGINAR VALORES
        ViewHolder data= (ViewHolder) convertView.getTag();//Con este metodo ya tengo la vista inflada ahora solo pongo valores
        BitmapManager.getInstance().loadBitmap(tweets.get(position).getProfileImageUrl(),data.avatar);
        data.name.setText(tweets.get(position).getName());
        data.text.setText(tweets.get(position).getText());
        data.screenName.setText("@"+tweets.get(position).getScreenName());
        data.createdAt.setText(DateUtils.setDateFormat(tweets.get(position).getCreatedAt()));

        return convertView;
    }
}
