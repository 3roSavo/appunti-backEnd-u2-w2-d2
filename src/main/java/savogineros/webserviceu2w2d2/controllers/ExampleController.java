package savogineros.webserviceu2w2d2.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import savogineros.webserviceu2w2d2.entities.User;


@RestController // Serve per definire una "collezione" di endpoints (saranno i vari metodi di questa classe)
@RequestMapping("/examples")
// http://localhost3001/examples
// RequestMapping Serve per definire un prefisso comune nell'URL di tutti gli endpoints di questo controller, /examples in questo caso.
// Facciamo tutti endpoint di esempi quindi ha senso chiamarlo examples.
// Attenzione, il mio url sarà quindi http://localhost:8080 + /examples
// la nostra porta è la 8080 di default, se voglio modificarla vado su application.properties e aggiungo server.port=3001 (per esempio)
public class ExampleController {

    @GetMapping("/getExample") // quindi annotando così questo metodo risponderà alle GET, e l'url sarà quindi
    // http://localhost3001/examples/getExample
    // ATTENZIONE! Il metodo può chiamarsi in qualsiasi modo! il valore dell'url è dato qui sopra, cioè /getExample
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT) //<-- Serve per customizzare lo status code
    public String getExample() {
        System.out.println("prova della GET");
        return "Questa è una prova GET";

    }
    @PostMapping("/postExample")
    public String postExample() {
        System.out.println("prova della POST");
        return "prova di una post!!!!";
    }
    @GetMapping("/")
    public String get() {
        System.out.println("GET!!!");
        return "GET!!!";
    }
    @PostMapping("/")
    @ResponseStatus(HttpStatus.FORBIDDEN) // Posso personalizzare lo status code a piacimento
    public String post() {
        System.out.println("POST!!!");
        return "POST!!!";
    }
    @PutMapping("/")
    public String put() {
        System.out.println("PUT!!!");
        return "PUT!!!";
    }
    @PatchMapping("/")
    public String patch() {
        System.out.println("PATCH!!!");
        return "PATCH!!!";
    }
    @DeleteMapping("/")
    public String delete() {
        System.out.println("DELETE!!!");
        return "DELETE!!!";
    }

    // QUERY PARAMS

    @GetMapping("/queryParamsExample")
    // http://localhost3001/examples/queryParamsExample
    public String queryParamsExample(@RequestParam String name, @RequestParam(required = false) String surname) { // http://localhost3001/examples/queryParamsExample?name=[valore]&surname=[valore]
        // ATTENZIONE nel mio url i params name e surname si riferiscono ai parametri qui sopra e sono case sensitive,
        // cioè devono essere IDENTICI a come dichiarati nel metodo sopra.
        // Per dichiarare un params si usa ?, per dargli il valore si usa =, per aggiungere un altro parametro in sequenza si usa &.
        // @RequestParam serve per rendere obbligatorio il parametro nell'url, se non trova un parametro annotato @RequestParam ritorna Bad request(400)
        // @RequestParam(required = false) rende il parametro facoltativo, in caso di mancanza di un parametro sarà sostituito con null
        System.out.println("Hello my name is " + name + " " + surname); // Stampo in console
        return "Hello my name is " + name + " " + surname; // Ritorna nel body della response
    }

    // PATH PARAMS

    @GetMapping("/pathParamsExample/{id}")
    // http://localhost3001/examples/pathParamsExample/123456789
    public String pathParamsExample(@PathVariable String id) {
        return "Stai cercando l'utente con id " + id;
    // Che succede qui? Nel contesto di un'applicazione web, quando definisci un endpoint che accetta path parameters,
    // solitamente li specifichi nella definizione dell'URL. Utilizzando una sintassi specifica
    // (ad esempio, {parametro} o :parametro), dichiari che quel segmento dell'URL è un parametro variabile e può
    // assumere un valore specifico quando viene effettuata la richiesta.

    //In questo esempio, id è un path parameter e viene estratto dalla parte del percorso dell'URL
    // e utilizzato come parametro input per il metodo pathParamsExample.
    // Di default il parametro estratto sarà di tip String!
    // Il nome del parametro è a tua discrezione, ricordati però l'annotazione PathVariable
    // Tieni presente che su Postman (e sul web?) le Path Variables si scrivono es= :id, mentre su java e Spring {id}
    }

    @PostMapping("/payloadExample")
    // http://localhost:3001/examples/payloadExample     (+ body obbligatorio)
    public String payloadExample(@RequestBody User body) {
        System.out.println(body);
        return "";
    // ATTENZIONE, ricordati che il body non fa parte dell'url! La richiesta si concentra nel body
    // Quindi per l'endpoint /payloadExample il metodo payloadExample() richiede un body di tipo User e un ritorno String
    // Essendo il parametro annotato come @RequestBody richiede un corpo, un body. Quindi quantomeno
    // dovrò inserire un JSON vuoto {} nel body della richiesta così da soddisfare l'obbligo. Spring è in grado di
    // trasformare (parse) facilmente i JSON in oggetti, prova a passargli come corpo name id o surname.
    }
    @PostMapping("/payloadExample2")
    public User payloadExample2(@RequestBody User body) {
        System.out.println(body);
        return body;
    // In questo caso per l'endpoint /payloadExample il metodo payloadExample() richiede un body di tipo User e
    // anche un ritorno di tipo User. Estratto lo User dal body della richiesta opportunamente trasformato da Spring
    // viene anche ritornato. Con body vuoto {} mi ritorna un JSON con un User vuoto. Se nella richiesta inserisco un
    // JSON con proprietà compatibili con quelle dello User Spring le associa automaticamente.
    }

    // ESEMPIO DI AGGIUNTA CUSTOM HEADER

    @PostMapping("/payloadExampleWithCustomHeaders")
    // http://localhost:3001/examples/payloadExampleWithCustomHeaders     (+ body obbligatorio)
    public ResponseEntity<User> payloadExampleWithCustomHeaders(@RequestBody User body) {
        HttpHeaders customHeaders = new HttpHeaders();
        customHeaders.add("myCustomHeader", "customValue");
        return new ResponseEntity<>(body,customHeaders, HttpStatus.OK);
        // Uso ResponseEntity quando voglio customizzare tutti gli aspetti della response compresi gli headers
    }










}
