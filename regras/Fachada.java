package regras;


import gui.Casa;


public class Fachada {
    CtrlRegras ctrl;
    static Fachada f=null;

    private Fachada() {
        ctrl=new CtrlRegras();
    }

    public static Fachada getFachada() {
        if(f==null) {
            f=new Fachada();
        }
        return f;
    }

    public Casa[][] getTabuleiro() {
        return ctrl.getTabuleiro();
    }
    
    public Casa[][] getTabuleiro2(){
		return ctrl.getTabuleiro2();
	}

    public void register(Observer o) {
        ctrl.addObserver(o);
    }

    public String[] getJogadores(){ return ctrl.getJogadores();}

    public void setJogadores(String jog1, String jog2) {
        ctrl.setJogadores(jog1, jog2);
    }
    
    public int getArmasNoTabuleiro() {
    	return ctrl.getArmasNoTabuleiro();
    }
    
    public void setArmasNoTabuleiro() {
    	ctrl.setArmasNoTabuleiro();
    }
    
	public void resetaArmasNoTabuleiro() {
		ctrl.resetaArmasNoTabuleiro();
	}
	
	public int getAtaques() {
		return ctrl.getAtaques();
	}
	
	public void setAtaques() {
		ctrl.setAtaques();
	}
	
	public int getVez() {
		return ctrl.getVez();
	}
	
	public void setVez() {
		ctrl.setVez();
	}
	
	public void setArmasTab1() {
		ctrl.setArmasTab1();
	}
	
	public void setArmasTab2() {
		ctrl.setArmasTab2();
	}
	
	public int getArmasTab1() {
		return ctrl.getArmasTab1();
	}
	
	public int getArmasTab2() {
		return ctrl.getArmasTab2();
	}
	
	public void salvaTabuleiros() {
		ctrl.salvaTabuleiros();
	}
	
	public Fachada carregaTabuleiro() {
		ctrl = ctrl.carregaTabuleiros();
		return f;
	}
	
	public void resetaFachada() {
		ctrl = new CtrlRegras();
	}
}
