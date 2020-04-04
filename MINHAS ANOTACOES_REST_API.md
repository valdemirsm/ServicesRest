#Para criar o primeiro serviço basta usar três anotações.
@RestController - colocar na classe
@RequestMapping("/teste") - colocar na classe, define o nome do recurso
@GetMapping coloca no método, define o método de busca do recurso.
#Chama assim http://localhost:8080/teste

-------------------------------------------------
#Recebe parâmetro da url, o nome id deve ser igual o que recebe e que esta assinatura no metodo.
@GetMapping("/{id}")
public String testando(@PathVariable Long id) {
	return "Porta" + id;
}

----------------------------------
#Produz como resposta um xml ou json, deve apenas anotar encima do método, lembrando que o padrão e json, mesmo que não informe nada.
@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)

-------------------------------
#Define novo nome para as propriedades que irá aparecer no json ou xml.
@JsonProperty(value  = "descriacao")
private String nome;

-------------------------------
#Ignora o atributo no momento se serializar, ou seja, na hora de gerar a representação json ou xml.
@JsonIgnore
private String nome;

--------
#Métodos HTTP, existem dois tipos, os idepotente (quando envia varias requisições com mesmos valores e não altera o estado do objeto a partir da primeira requisição) e os não idepotente (quando todas as requisição altera o estado do objeto)
Idepotente são eles: GET(retorna recurso), PUT(altera recurso), PATCH(altera apenas os atributos passado), DELETE(exclui um recurso), OPTIONS(retorna quais os métodos http disponivel para o recurso.)
Não Idepotente: POST(cria um novo recurso)

#Podemos dizer tambem que todos os métodos que altera o estado do objeto são métodos não seguros, como PUT, PATCH, DELETE, POST

-----------------
#Principal status http
200 - requisição bem sucedida
201 - novo recurso criado com sucesso
204 - servidor processou a requisição com sucesso mas não retornou nada.
400 - servidor não pode processar a requisição, talvez por erro de sintaxe.
404 - o recurso solicitado não foi encontrado, pode ser erro na url
500 - erro interno no servidor, pode ser erro de código como um NullPointerExeption

-----------------------
#Duas formas de retornar o status http.
return ResponseEntity.status(HttpStatus.OK).body(cliente); -- retorna 200
return ResponseEntity.ok(cliente); -- retorna 200

#Caso um recurso não existir no banco de dados
return ResponseEntity.notFound().build(); -- retorna 204

#A assinatura do método deve ficar assim
ResponseEntity<Cliente> testando()

--------------------------
#Adiciona um novo recurso.
@PostMapping
public ResponseEntity<Cliente> adicionar(@RequestBody Cliente cliente) {
	return ResponseEntity.status(HttpStatus.CREATED).body(cliente); // retorna status 201
}

-------------------------------
#Atualiza um recurso
@PutMapping("/{id}")
public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
	Cliente clienteAtual = new Cliente();
	clienteAtual.setId(id);
	BeanUtils.copyProperties(cliente, clienteAtual, "id"); // atualiza clienteAtual e ignora o campo id
	return ResponseEntity.status(HttpStatus.OK).body(clienteAtual); // retorna status 200
}

------------
#Exclui um recurso
@DeleteMapping("/{id}")
public ResponseEntity<Cliente> remover(@PathVariable Long id) {
	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();// retorna status 204
}
	
---------------
#Atualizando dados parcialmente, foi utilizado reflect para atualizar o valor dos campos de uma entidade.

@PatchMapping("/{clineteId}")
public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long clineteId, @RequestBody Map<String, Object> campos) {
	Cliente clienteAtual = new Cliente();
	clienteAtual.setId(clineteId);
	merge(campos, clienteAtual);
	return ResponseEntity.status(HttpStatus.OK).body(clienteAtual);
}

private void merge(Map<String, Object> campos, Cliente clienteDestino) {
	ObjectMapper objectMapper = new ObjectMapper();
	Cliente convertValue = objectMapper.convertValue(campos, Cliente.class);
	System.out.println(convertValue);
	campos.forEach((nomePropriedade, valorPropriedade) -> {
		Field field = ReflectionUtils.findField(Cliente.class, nomePropriedade);
		field.setAccessible(true); // torna o atributo acessivel para alterar o valor
		Object novoValor = ReflectionUtils.getField(field, convertValue);
		ReflectionUtils.setField(field, clienteDestino, novoValor);
	}); 
}
