package com.reserva;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity implements OnSeekBarChangeListener,
		OnClickListener, OnDateSetListener, OnTimeSetListener {

	EditText nombre;
	TextView cuantasPersonas,datos;
	Button fecha, hora;
	SeekBar barraPersonas;

	SimpleDateFormat horaFormato, fechaFormato;

	String nombreReserva = "";
	String numPersonas = "";
	String fechaSel = "", horaSel = "";
	Date fechaConv;
	String cuantasPersonasFormat = "";
	int personas = 1; // Valor por omision, al menos 1 persona tiene que reservar

	Calendar calendario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cuantasPersonas = (TextView) findViewById(R.id.cuantasPersonas);
		barraPersonas = (SeekBar) findViewById(R.id.personas);

		fecha = (Button) findViewById(R.id.fecha);
		hora = (Button) findViewById(R.id.hora);

		barraPersonas.setOnSeekBarChangeListener(this);

		nombre = (EditText) findViewById(R.id.nombre);

		cuantasPersonasFormat = cuantasPersonas.getText().toString();
		// cuantasPersonasFormat = "personas: %d";
		cuantasPersonas.setText("Personas: 1"); // condicion inicial

		// Para seleccionar la fecha y la hora
		Calendar fechaSeleccionada = Calendar.getInstance();
		fechaSeleccionada.set(Calendar.HOUR_OF_DAY, 12); // hora inicial
		fechaSeleccionada.clear(Calendar.MINUTE); // 0
		fechaSeleccionada.clear(Calendar.SECOND); // 0
        datos= (TextView) findViewById(R.id.textView4);
        datos.setText("");
		// formatos de la fecha y hora
		fechaFormato = new SimpleDateFormat(fecha.getText().toString());
		horaFormato = new SimpleDateFormat("HH:mm");
		// horaFormato = new SimpleDateFormat(hora.getText().toString());

		// La primera vez mostramos la fecha actual
		Date fechaReservacion = fechaSeleccionada.getTime();
		fechaSel = fechaFormato.format(fechaReservacion);
		fecha.setText(fechaSel); // fecha en el

		horaSel = horaFormato.format(fechaReservacion);
		// boton
		hora.setText(horaSel); // hora en el boton

		// Otra forma de ocupar los botones
		fecha.setOnClickListener(this);
		hora.setOnClickListener(this);

	}

	@Override
	public void onProgressChanged(SeekBar barra, int progreso,
			boolean delUsuario) {

		numPersonas = String.format(cuantasPersonasFormat,
				barraPersonas.getProgress() + 1);
		personas = barraPersonas.getProgress() + 1; // este es el valor que se
													// guardara en la BD
		// Si no se mueve la barra, enviamos el valor personas = 1
		cuantasPersonas.setText(numPersonas);
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
	}

	@Override
	public void onClick(View v) {
		if (v == fecha) {
			Calendar calendario = parseCalendar(fecha.getText(), fechaFormato);
			new DatePickerDialog(this, this, calendario.get(Calendar.YEAR),
					calendario.get(Calendar.MONTH),
					calendario.get(Calendar.DAY_OF_MONTH)).show();
		} else if (v == hora) {
			Calendar calendario = parseCalendar(hora.getText(), horaFormato);
			new TimePickerDialog(this, this,
					calendario.get(Calendar.HOUR_OF_DAY),
					calendario.get(Calendar.MINUTE), false) // /true = 24 horas
					.show();
		}
	}

	private Calendar parseCalendar(CharSequence text,
			SimpleDateFormat fechaFormat2) {
		try {
			fechaConv = fechaFormat2.parse(text.toString());
		} catch (ParseException e) { // import java.text.ParsedExc
			throw new RuntimeException(e);
		}
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fechaConv);
		return calendario;
	}

	@Override
	public void onDateSet(DatePicker picker, int anio, int mes, int dia) {
		calendario = Calendar.getInstance();
		calendario.set(Calendar.YEAR, anio);
		calendario.set(Calendar.MONTH, mes);
		calendario.set(Calendar.DAY_OF_MONTH, dia);

		fechaSel = fechaFormato.format(calendario.getTime());
		fecha.setText(fechaSel);

	}

	public void onTimeSet(TimePicker picker, int horas, int minutos) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, horas);
		calendar.set(Calendar.MINUTE, minutos);

		horaSel = horaFormato.format(calendar.getTime());
		hora.setText(horaSel);
	}

	public void reserva(View v) {
		Intent envia = new Intent(this, Actividad2.class);
		Bundle datos = new Bundle();
		datos.putString("nombre", nombre.getText().toString().trim());
		datos.putInt("personas", personas);
		datos.putString("fecha", fechaSel);
		datos.putString("hora", horaSel);
		envia.putExtras(datos);
		finish();
		startActivity(envia);
	}

    public void registro(View v) {
        Intent envia = new Intent(this, registro.class);

        finish();
        startActivity(envia);
    }
    public void Tabla(View view){
      //  SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version);
//		Create a helper object to create, open, and/or manage a database.
		SQLHelp sql = new SQLHelp(this,	"DB_Restaurant", null, 1);
		final SQLiteDatabase db = sql.getReadableDatabase();
     	String[] campos = {"_id", "Nombre", "Pass"};
        //query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
//		Query the given table, returning a Cursor over the result set.
		Cursor selectAll = db.query("Reservacion", campos, null, null, null, null, null);
//
		int Total = selectAll.getCount();
        datos.setText("");
		for(int i = 9; i <= Total; i++){
			selectAll.moveToNext();
			String nombreSelect = selectAll.getString(1);
			datos.append(selectAll.getInt(0) + "Nombre: "+nombreSelect + " Contraseña: " +	selectAll.getString(2) +"\n");
		}
		db.close();
    }
}
