package br.edu.ifsp.sdm.manhani.omdbsdm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifsp.sdm.manhani.omdbsdm.adapter.ListaFilmesAdapter
import br.edu.ifsp.sdm.manhani.omdbsdm.dto.RetornoPesquisa
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_pesquisa.*
import kotlinx.android.synthetic.main.fragment_pesquisa.view.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.design.snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PesquisaFragment : Fragment() {

    lateinit var filmeAdapter: ListaFilmesAdapter
//    private var filmes = arrayOf(MutableList<Filme>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_pesquisa, container, false)
        layout.button.setOnClickListener {
            val texto = layout.editTextPesquisa.text.toString()
            if(texto.isNotBlank()) {
                pesquisarFilme(layout.editTextPesquisa.text.toString())
            }else{
                activity?.mainContent?.snackbar("Digite o nome do filmes para realizar a pesquisa.")
            }
        }
        return layout
    }

    private val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    var callback: RetornoCallback? = null
    // Instanciando o cliente HTTP
    init {
        // Adiciona um interceptador que é um objeto de uma classe anônima
        okHttpClientBuilder.addInterceptor { chain ->
            // Resgatando requisição interceptada
            val reqInterceptada: Request = chain.request()
            // Criando nova requisição a partir da interceptada e adicionando campos de cabeçalho
            val url = reqInterceptada.url().newBuilder().addQueryParameter(Constantes.KEY_FIELD, Constantes.KEY_VALUE).build()
            val novaReq: Request = reqInterceptada.newBuilder()
                .url(url)
                .method(reqInterceptada.method(), reqInterceptada.body())
                .build()
            // retornando a nova requisição preenchdia
            chain.proceed(novaReq)
        }

        callback = object : RetornoCallback {
            override fun onResponse(obj: RetornoPesquisa) {
                if(obj.filmes.isNotEmpty()) {
                    recyclerViewFilmes.apply {
                        layoutManager = LinearLayoutManager(activity)
                        adapter = ListaFilmesAdapter(obj.filmes)
                    }
                }else{
                    activity?.mainContent?.snackbar("Nenhum resultado encontrado. ")
                }
            }
        }
    }

    // Novo objeto Retrofit usando a URL base e o HttpClient com interceptador
    val retrofit: Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constantes.URL_BASE).client(okHttpClientBuilder.build()).build()


    // Cria um objeto, a partir da Interface Retrofit, que contém as funções de requisição
    val omdbApi: OmdbApi = retrofit.create(OmdbApi::class.java)

    fun pesquisarFilme(titulo: String) {
        /*Chama a função de requisição definida na Interface passando os parâmetros escolhidos pelo usuário e
        enfileira a requisição que recebe um objeto de uma implementação anônima de Callback<ResponseBody>*/

        omdbApi.getFilmes(titulo).enqueue(
            object : Callback<RetornoPesquisa> {
                override fun onFailure(call: Call<RetornoPesquisa>, t: Throwable) {
                    activity?.mainContent?.snackbar("Erro: " + t.message)
                }
                override fun onResponse(call: Call<RetornoPesquisa>, response: Response<RetornoPesquisa>) {
                    val body = response.body()
                    if (body != null) {
                        callback?.onResponse(body)
                    }
                }
            } // Fim da classe anônima
        ) // Fim dos parâmetros de enqueue
    } // Fim da função traduzir

    interface RetornoCallback {
        fun onResponse(obj: RetornoPesquisa)
    }

}
