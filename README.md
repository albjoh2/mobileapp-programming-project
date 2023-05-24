
# Rapport

##Om Appen
Jag valde att göra en applikation där studenter lätt och snabbt kan träna inför tentamen.
Applikationen fungerar ungefär som ett så kallat flashcard med en fråga som visas i en lista
på skärmen och studenten ska sedan svara på frågan och kan där efter trycka på frågan för
att få fram det rätta svaret och säkerställa att det man svarat stämmer.

##Disclamer
Under arbetet med projektet har tekniska problem vart närvarande där emulatorn
tidvis inte fungerat och gradle versionen ställt till problem samt att emulatorns internet
uppkoppling tidvis inte fungerat. Det har gjort att jag gjort om mitt projekt ett flertal
gånger, att mina commits ligger nära varandra i tid.

##Implementation
###Layout
Applikationen består utav två sidor, startsidan med en navbar högst upp som
består utav en rubrik med appens namn, två knappar för att navigera mellan 
appens aktiviteter och ett pluss som är tänkt att kunna användas för att lägga
till nya frågor och svar i applikationen, idag har den ingen funtionalitet
utan ligger där för att förtydliga tanken med applikationen. Startsidans kropp
består sedan utav en större rubrik som guidar användaren till hur appen fungerar. 
Det finns en recycler view som innehåller frågorna och en bild på en hjärna som ger
appen lite karaktär. När man trycker på en fråga dyker en text upp med frågans svar
längst ner på sidan.

![](Skärmavbild.png)
![](SkärmbildSvar.png)


Sida två är About-sidan som innehåller en likadan navbar som sida 1 och en rubrik samt en text
som beskriver appens tilltänkta målgrupp.
![](SkärmbildAbout.png)

###Data
I Main activity classen hämtas datan, detta görs direkt då applikationen startas upp. En variabel
håller den URL som tatan ska hämtas ifrån:
   
    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a22albjo";

I onCreate metoden skapas en ny instans av JsonTask med JSON_URL som parameter. 

    new JsonTask(this).execute(JSON_URL);

JsonTask hämtar datan och kör onPostExecute metoden vilket den skickar med en stingifierad version
av JSON datan. En Gson instans skapas och en tillhörande typ, dessa kombineras för att populera
variabeln items med instanser av min Question klass.

    public void onPostExecute(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>() {
        }.getType();
        ArrayList<Question> items = gson.fromJson(json, type);

En instans av RecyclerViewAdapter skapas med en onClick som visar frågans svar när man trycker på
den i recyclerviewn.

        adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Question item) {
                Toast.makeText(MainActivity.this, item.info(), Toast.LENGTH_LONG).show();
            }
        });
        adapter.notifyDataSetChanged();

Adaptern appliceras slutligen på recyclerViewn

        RecyclerView view = findViewById(R.id.recyclerview);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);
    }


###Activity
För att applikationen ska hantera övergången från en aktivitet till en annan används en intent.
I onCreate metoden initieras variabeln aboutButton som håller en referens till knappen som används 
för att navigera. 

    aboutButton = findViewById(R.id.button1);

En lyssnare sätts på knappen för att lyssna efter klick.

    aboutButton.setOnClickListener(new View.OnClickListener() {

En metod specificerar vad som ska ske när knappen trycks ned, ett nytt intent skapas från vår
MainActivity till vår AboutActivity. Sedan körs intentet viket skickar oss till About sidan.

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);

På about sidan finns likadan kod fast med en annan knapp och med ett motsatt intent.
