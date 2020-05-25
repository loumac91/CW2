package q1;

import java.text.Collator;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    // Q1. Implement a bubble sort algorithm for a list of names in Java.
    // e.g ["Michael", "Zack", "Rachid"] becomes ["Michael", "Rachid", "Zack"]


    String[] inputOne = new String[] {"Michael", "Zack", "Rachid"};
    String[] inputTwo = new String[] {"Ben", "Zac", "Michael", "Zack", "Rachid", "Louis", "Jose", "Andrii", "Elizabeth", "Rodney", "Ronald", "Aandrew", "Aabbi", "Seb"};
    String[] inputThree = new String[] {"Ben", "Zac", "Michael", "Zack", "Rachid"};
    // String[] result = BubbleSort.sort(inputThree);

    // for (String string : result) {
    //   System.out.println(string);
    // }

    String x = "";


    String[] inputFour = new String[] {"Dewey", "Julieta", "Humberto", "Chin", "Liberty", "Dorethea", "Timothy", "Laveta", "Kathi", "Kaila", "Analisa", "Ismael", "Aletha", "Pasquale", "Neta", "Junko", "Veronika", "Warner", "Lilian", "Cristopher", "Raisa", "Maude", "Inez", "Elouise", "Berniece", "Reinaldo", "Lashanda", "Dalia", "Kimbery", "Janetta", "Kristina", "Rodrick", "Clemmie", "Eldridge", "Jung", "Margarete", "Elizabet", "Talitha", "Dwain", "Madelene", "Lorina", "Maye", "Marinda", "Elizabeth", "Michelina", "Lyn", "Tobie", "Nanci", "Kera", "Johann", "Ardelia", "Piedad", "Pilar", "Nina", "Marivel", "Delisa", "Mellissa", "Randolph", "Norine", "Robyn", "Chanelle", "Britni", "Soledad", "Stanton", "Alejandro", "Mose", "Grace", "Emilia", "Joetta", "Natividad", "Jayne", "Ludivina", "Alexandra", "Charline", "Sook", "Pamella", "Colette", "Margit", "Von", "Candra", "Lise", "Maribeth", "Genevie", "Brittanie", "Vance", "Jason", "Lucile", "Temika", "Maryam", "Vernie", "Cherish", "Onie", "Miyoko", "Nikia", "Georgann", "Dean", "Britany", "Roselle", "Haywood", "Goldie", "Kalyn", "Aimee", "Brice", "Gudrun", "Yang", "Jung", "Grisel", "Stacia", "Lachelle", "Terisa", "Evette", "Savanna", "Alex", "Cleora", "Andre", "Joe", "Josephina", "Daryl", "Yevette", "Stacey", "Sena", "Christene", "Makeda", "Betty", "Darci", "Criselda", "Aaron", "Tandy", "Kerstin", "Christen", "Melany", "Cherly", "Eleonore", "Lakenya", "Yer", "Krystle", "Elenora", "Zelma", "Lorinda", "Jon", "Elia", "Wm", "Lorean", "Pearlie", "Nicolasa", "Tiffanie", "Odell", "Chad", "Lurlene", "Selene", "Genoveva", "Mike", "Efren", "Rozella", "Vonnie", "Cythia", "Conrad", "Porter", "Maximina", "Aisha", "Cassaundra", "Emmy", "Adrianna", "Douglass", "Terrell", "Nanette", "Denita", "Timmy", "Dani", "Dorthy", "Tilda", "Ardelle", "Jone", "Adan", "So", "Ricardo", "Krystyna", "Cristine", "Rosaura", "Wilbur", "Isidro", "Tiffany", "Carlyn", "Carole", "Eddy", "Lucrecia", "Kathyrn", "Milton", "Johnetta", "Maira", "Zelda", "Mirna", "Gaylene", "Marsha", "Krystina", "Emmitt", "Russel", "Rubin", "Sol", "Signe", "Hortencia", "Delicia", "Meredith", "Jovita", "Janey", "Alessandra", "Alfonzo", "Joesph", "Matt", "Tangela", "Chastity", "Lacey", "Wilburn", "Magdalene", "Bryce", "Solomon", "Lanora", "Brigitte", "Jone", "Teofila", "Jeanine", "Emiko", "Glendora", "Antwan", "Exie", "Rickie", "Maggie", "Freda", "Cherise", "Lamonica", "Stevie", "Ed", "Ludivina", "Judith", "Alycia", "Troy", "Lindsy", "Cathie", "Margrett", "Gerald", "Euna", "Latrice", "Bryan", "Shelly", "Shawnee", "Laveta", "August", "Shane", "Lizabeth", "Nikki", "Phil", "Valerie", "Loren", "Bruna", "Kyung", "Martha", "Marita", "Rebbecca", "Alline", "Nadia", "Vena", "Talitha", "Tressie", "Kasha", "Emmitt", "Rocio", "Chantay", "Shon", "Florida", "Kina", "Mariela", "Jesusa", "Yun", "Sharen", "Maribeth", "Lenard", "Britteny", "Luigi", "Hans", "Shonda", "Elenora", "Trent", "Dona", "Fran", "Rosenda", "Twanna", "Arleen", "Forest", "Amal", "Nick", "Manie", "Zola", "Wilburn", "Brittaney", "Artie", "Yi", "Sarina", "Riva", "Digna", "Cinthia", "Jay", "Shelby", "Elizebeth", "Wilhemina", "Kirstie", "Dorthea", "Veronica", "Maricruz", "Harlan", "Caridad", "Suellen", "Ruthe", "Carmella", "Dulcie", "See", "Osvaldo", "Carla", "Stefania", "Raphael", "Clarissa", "Cuc", "Shon", "Thurman", "Mercedes", "Salvador", "Moshe", "Azalee", "Thomas", "Ellyn", "Twila", "Lorine", "Tamesha", "Adrian", "Dawna", "Cathleen", "Margene", "Chastity", "Ruthann", "Lorilee", "Mafalda", "Loree", "Ashleigh", "Patty", "Sharyn", "Particia", "Katlyn", "Tisa", "Shantell", "Renaldo", "Miesha", "Viki", "Donnetta", "Georgene", "Cortez", "Etsuko", "Gidget", "Kathern", "Terri", "Clemente", "Elvis", "Leonila", "Candida", "Addie", "Natisha", "Marleen", "Jeffrey", "Pamela", "Burt", "Jaimie", "Iesha", "Latosha", "Tomasa", "Cleopatra", "Katheryn", "Shoshana", "Lilliana", "Malcolm", "Dorthey", "Julee", "Brook", "Tawny", "Juana", "Ginette", "Celsa", "Johnathan", "Lance", "Kaley", "Mitsue", "Phyllis", "Sonja", "Glinda", "Domonique", "Dortha", "Kymberly", "Elisa", "Hung", "Junie", "Voncile", "Rosanne", "Cecille", "Patrice", "Antwan", "Raleigh", "Tashina", "Jamal", "Verdie", "Van", "Maureen", "Faye", "Cheree", "Karren", "Fidelia", "Desmond", "Tennille", "Fred", "Jonathon", "Stephane", "Merilyn", "Kelsey", "Ernest", "Tasha", "Aundrea", "Hiram", "Orville", "Deeann", "Jewell", "Malcolm", "Wilma", "Wai", "Porfirio", "Micheal", "Delbert", "Anton", "Socorro", "Ruthe", "Estelle", "Marylyn", "Mariano", "Jean", "Sheilah", "Trula", "Thu", "Marcel", "Alejandro", "Marylouise", "Arielle", "Erminia", "Mirtha", "Lyman", "Cher", "Miquel", "Anjanette", "Jolene", "Mercy", "Demetrice", "Elmira", "Carol", "Madeline", "Siobhan", "Jason", "Marylynn", "Everette", "Mitch", "Luther", "Dovie", "Bryan", "Jennette", "Tempie", "Roslyn", "Hilary", "Oma", "Bonita", "Felipe", "Janette", "Danika", "Sandee", "Leanne", "Edwin", "Faith", "Yi", "Tanya", "Treasa", "Herbert", "Julianna", "Paulene", "Louie", "Florentina", "Ria", "Georgeanna", "Erna", "Roma", "Lenard", "Vinita", "Roselle", "Renata", "Kindra", "Twila", "Violet", "Alexandria", "Pamela"};
    
    
    Instant starts = Instant.now();
    String[] result = BubbleSort.sort(inputThree, SortOrder.ASCENDING);
    Instant ends = Instant.now();
    // System.out.println(Duration.between(starts, ends));
    // for (String string : result) {
    //   System.out.println(string);
    // }
  }
}