<h1 align='center'>Consumindo uma API! 🌐</h1>

Este projeto foi desenvolvido com o objetivo de servir como estudo, utilizando a API do ViaCep para criar uma consulta e retornar uma resposta. A resposta será exibida no console e salva em um arquivo JSON para futuras consultas.


<details>
  <summary> <b>O que foi utilizado para o desenvolvimento? </b> </summary>
  
  <br>
  
* <a target="_blank" href='https://carrossel-de-musicas.vercel.app/'>API: ViaCep</a>
* <a target="_blank" href= 'https://docs.oracle.com/en/java/javase/17/docs/api/java.net.http/java/net/http/HttpRequest.html'>HTTP Request</a>
* <a target="_blank" href= 'https://docs.oracle.com/en/java/javase/17/docs/api/java.net.http/java/net/http/HttpResponse.html'>HTTP Response</a>
* <a target="_blank" href= 'https://mvnrepository.com/'>Maven Repository</a>
* <a target="_blank" href= 'https://github.com/google/gson/blob/main/UserGuide.md'>Gson</a>
* <a target="_blank" href= 'https://docs.oracle.com/en/java/javase/17/docs/api/index.html'>JavaDoc</a>
* <a target="_blank" href= 'https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Record.html'>Record</a>
* <a target="_blank" href= 'https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/FileWriter.html'>FileWriter</a>
* <a target="_blank" href= 'https://docs.oracle.com/en/java/javase/17/docs/api/index.html'>JavaDoc</a>
* <a target="_blank" href= 'https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/LinkedList.html'>LinkedList</a>
* <a target="_blank" href= 'https://www.alura.com.br/artigos/decorando-terminal-cores-emojis?srsltid=AfmBOooA4rkE4rfiw16dWuZyj877kNs-fcWsVwcET-mS258YpntRukKG'>Cores ANSI</a>  
</details>

<details>
  <summary> <b>Observações: </b> </summary>
  <br>
   É recomendável utilizar, no mínimo, a versão 13 do Java para rodar este projeto. Caso utilize a versão 17, o projeto funcionará perfeitamente.
  
</details>

<details>
  <summary> <b>Melhorias futuras e erros conhecidos: </b> </summary>
  <br>
    Caso seja digitado um CEP inexistente, a requisição retornará um "BadRequest 400", resultando na ativação de uma exceção e encerramento da aplicação. Este comportamento já está mapeado para tratamento. Além disso, caso seja digitado um texto em vez de um CEP, a aplicação também falhará de forma não programada. Essas situações já são conhecidas e serão tratadas em breve para que a aplicação funcione da melhor maneira possível.

</details>



