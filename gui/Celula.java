package gui;

class Celula {
	double x,y;
	Armas arma;

	Celula(double x, double y) {
		this.x=x;
		this.y=y;
		this.arma = null;
	}

	public Armas getArma() {
		return arma;
	}

	public void setArma(Armas arma) {
		this.arma = arma;
	}

}
