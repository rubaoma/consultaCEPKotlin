package com.example.consultacepkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    //Widgets
    private var etCEP: EditText? = null
    private var etRua: EditText? = null
    private var etCidade: EditText? = null
    private var spUFs: Spinner? = null
    private var btnBuscarPorCEP: Button? = null
    private var btnBuscarPorRuaCidadeEstado: Button? = null
    private var progressBar: ProgressBar? = null
    private var arrayCEPs: ArrayList<CEP>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//Refs.
        etCEP = findViewById(R.id.et_cep)
        btnBuscarPorCEP = findViewById(R.id.btn_buscar_por_cep)
        progressBar = findViewById(R.id.progress_bar)
        etRua = findViewById(R.id.et_rua)
        etCidade = findViewById(R.id.et_cidade)
        spUFs = findViewById(R.id.sp_ufs)
        btnBuscarPorRuaCidadeEstado = findViewById(R.id.btn_buscar_por_rua_cidade_estado)
        progressBar?.setVisibility(View.INVISIBLE)
        btnBuscarPorCEP?.setOnClickListener(View.OnClickListener {
            if (!etCEP?.getText().toString().isEmpty()) {
                progressBar?.setVisibility(View.VISIBLE)
                val service = CEPService(this@MainActivity)
                service?.CEPService(getText().toString(), object : SimpleCallback<CEP?> {
                    override fun onResponse(response: CEP?) {

                        //Retorno na Toast
                        Toast.makeText(
                            applicationContext,
                            resources.getString(R.string.toast_aviso_retorno) + response.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        progressBar?.setVisibility(View.INVISIBLE)
                    }

                    override fun onError(error: String?) {
                        toast("erro onError: " + error.toString())
                        progressBar?.setVisibility(View.INVISIBLE)
                    }
                })
            } else {
                toast("CEP vazio!")
            }
        })

        /* Busca por Rua Cidade e Estado(UF) */
        btnBuscarPorRuaCidadeEstado?.setOnClickListener(
            View.OnClickListener {
                if (!etCidade?.getText().toString().isEmpty() &&
                    !etRua?.getText().toString().isEmpty() && spUFs?.getSelectedItemPosition() != 0
                ) {
                    progressBar?.setVisibility(View.VISIBLE)
                    val service = CEPService(this@MainActivity)
                    service.getCEPUFCidadeRua(spUFs?.getSelectedItem().toString(),
                        etCidade?.getText().toString(),
                        etRua?.getText().toString(), object : SimpleCallback<List<CEP?>?> {
                            override fun onResponse(response: List<CEP?>?) {
                                arrayCEPs = ArrayList()
                                for (cep in response!!) {
                                    if (cep != null) {
                                        arrayCEPs!!.add(cep)
                                    }
                                }
                                toast(resources.getString(R.string.toast_aviso_retorno) + arrayCEPs.toString())
                                progressBar?.setVisibility(View.INVISIBLE)
                            }

                            override fun onError(error: String?) {
                                toast(resources.getString(R.string.toast_erro_generico) + error)
                                progressBar?.setVisibility(View.INVISIBLE)
                            }
                        })
                }
            })
    } //oncreate

    private fun toast(msg: String) {
        Toast.makeText(baseContext, msg, Toast.LENGTH_LONG).show()
    }

    companion object {
        //Tag para o LOG
        private const val TAG = "logCEP"
    }
}




