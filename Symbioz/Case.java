import java.util.ArrayList;

public class Case
{	
	private int id;
	private int[] zerbs;
	private int[] crapits;
	private int[] kroguls;
	private boolean symbioz=false;
	private ArrayList<Case> casesAdjacentes;

	public Case(int id, int nbJoueurs)
	{
		this.id=id;
		this.zerbs = new int[nbJoueurs];
		this.crapits = new int[nbJoueurs];
		this.kroguls = new int[nbJoueurs];
		this.casesAdjacentes = new ArrayList<Case>();		

		for(int i = 0; i < nbJoueurs; i++)
		{
			this.zerbs[i] = 0;
			this.crapits[i] = 0;
			this.kroguls[i] = 0;
		}
	}

	public void addCaseAdjacente(Case c1, Case c2, Case c3)
	{
		this.casesAdjacentes.add(c1);
		this.casesAdjacentes.add(c2);
		this.casesAdjacentes.add(c3);
	}
	
	public void addCaseAdjacente(Case c1, Case c2, Case c3, Case c4)
	{
		this.casesAdjacentes.add(c1);
		this.casesAdjacentes.add(c2);
		this.casesAdjacentes.add(c3);
		this.casesAdjacentes.add(c4);
	}
	
	public void addCaseAdjacente(Case c1, Case c2, Case c3, Case c4, Case c5)
	{
		this.casesAdjacentes.add(c1);
		this.casesAdjacentes.add(c2);
		this.casesAdjacentes.add(c3);
		this.casesAdjacentes.add(c4);
		this.casesAdjacentes.add(c5);
	}


//////////////////////////////////////////////////////// TRIER TOUT CA

	public ArrayList<Case> getCasesAdjacentes()
	{
		return casesAdjacentes;
	}

	public int getId(){
		return id;
	}

	public int getZerbs(int idJoueur) {
		return zerbs[idJoueur];
	}

	public boolean setZerbs(int nbzerbs, int idJoueur) {
		int somme=0;
		for (int i=0; i<zerbs.length; i++)
		{
			somme+=zerbs[i];
		}
		if (nbzerbs+somme > 12)
		{
			System.out.println("Il y a deja "+somme+" zerbs dans cette case ");
			return false;
		}
		else {
			this.zerbs[idJoueur] += nbzerbs;
			return true;
		}
	}

	public int getCrapits(int idJoueur) {
		return crapits[idJoueur];
	}

	public void setCrapits(int nbcrapits, int idJoueur) {
		this.crapits[idJoueur] += nbcrapits;
	}

	public int getKroguls(int idJoueur) 
	{	
		return kroguls[idJoueur];
	}

	public void setKroguls(int nbkroguls, int idJoueur) {
		this.kroguls[idJoueur] += nbkroguls;
	}

	public void setSymbioz()
	{
		this.symbioz=true;
	}

	public boolean getSymbioz()
	{
		return this.symbioz;
	}
}
