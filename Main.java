import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	public static void main (String[] args) throws IOException {
		// A FAIRE gestion d'erreur pour input dans main et character, demandé le nom affichage complet des événements et affichage graphique

		
		Scanner userInputScanner = null;
		String in = "5";
		int roomLevel;
		Room room;
		int VectorSize = 10;
		Vector<Character> enemies = null;
		int currentEnemy;
		Factory usine = Factory.getInstance();
		String s = "";
		Character hero = null;
		String heroClass = null;
		
		System.out.println("Bienvenue Dans Clone Wars, Comment vous appelllez vous ?");
		
		userInputScanner = new Scanner(System.in);
		String name = userInputScanner.nextLine();
		System.out.println("Tres bien " + name + ", Choississez votre classe ?");
		System.out.println("Jawa ; Jedi ; ImperialAgent ; Sith ; BountyHunter ; Mercenary");
		

		while(hero == null)
		{
		heroClass = userInputScanner.nextLine();
		hero = Character.getInstance(heroClass);
		}
		hero.setName(name);
		System.out.println(heroClass);
		
		System.out.println("<--------  SCENARIO -------->");
		System.out.println("En pleine guerre galactique, vous etes envoyez sur une planete appelle Etna, cette planete est infestez de jawa.");
		System.out.println("Lors de votre arrivée vous croisez d'autres vaisseaux, surement ici pour la meme raison que vous, conquerir cette planete !");
		System.out.println("Vous avez debarquez avec vos troupes mais des projectiles venant des collines proches de votre position vous tombent dessus. Il s'agit d'un gaz toxique , surement un coup des jawa sauvages !");
		System.out.println("Par chance vous avez echappez a ce gaz mais vos troupes n'ont pas pu en rechapper.");
		System.out.println("Mais vos soldats vous tirs dessus ! Defendez vous !");
		System.out.println("<-------- -------->\n\n");
		
		for(roomLevel = 1; roomLevel != 11 && hero.getHp() > 0; roomLevel++) {
			s = "";
			room = new Room(roomLevel);
			//Entre dans la room
			while(!s.equals("2") && hero.getHp() > 0 && !in.equals("4"))
			{
				s = "";	
				enemies = usine.GenerateList(room, VectorSize);
			//Entre dans le vecteur de la room
				for(currentEnemy = 0; currentEnemy != VectorSize && hero.getHp() > 0 && !in.equals("4") && !s.equals("2"); currentEnemy++) {
					//Combat
					in = null;
					System.out.println("Un " + enemies.get(currentEnemy).getClass().getName() + " est apparu !");
					System.out.println( "Bonjour je suis " +enemies.get(currentEnemy).getName() + " , je vais te manger.\n\n");
					while(enemies.get(currentEnemy).getHp() > 0 && hero.getHp() > 0  && !s.equals("2")) 
					{
						s = "";
						System.out.println("Pour effectuez une action utiliser les touches claviers ou bien les boutons prevu a cet effet.");
						System.out.println("> 1 pour attaquer\n> 2 pour lancer l'attaque special\n> 3 pour que le Joueur choississent lui meme une attaque\n> 4 pour Quitter le jeu");
						

						while(in == null)
						{
							in = hero.attack(enemies.get(currentEnemy), userInputScanner);
						}

						if(in.equals("4"))
						{
							break;	
						}

							
							System.out.println("Points de vie restant a l'ennemi : " + enemies.get(currentEnemy).getHp());
							if(enemies.get(currentEnemy).getHp() > 0)
								enemies.get(currentEnemy).randomattack(hero);
							System.out.println("Vos points de vie restant : "+hero.getHp());
							//Fin du combat 1
							if(enemies.get(currentEnemy).getHp() <= 0)
							{
								System.out.println("enemy dead");
								hero.setXp(hero.getXp() + 150 + 100*enemies.get(currentEnemy).getLvl());
								if(hero.getXp() > 500 * (hero.getLvl()+1) + 500 * Math.pow((hero.getLvl()+1), 2))
									hero.levelUp();
								System.out.println("1 : stay\n2 : go next level");
								while(!s.equals("1") && !s.equals("2"))
									s = userInputScanner.nextLine();
							
						}
							
					}
				}			
			}
		}
		userInputScanner.close();
		//Fin du combat 2
		if(hero.getHp() <= 0)
			System.out.println("Game Over");
	}
}
