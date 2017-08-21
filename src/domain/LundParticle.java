/*  +__^_________,_________,_____,________^-.-------------------,
 *  | |||||||||   `--------'     |          |                   O
 *  `+-------------USMC----------^----------|___________________|
 *    `\_,---------,---------,--------------'
 *      / X MK X /'|       /'
 *     / X MK X /  `\    /'
 *    / X MK X /`-------'
 *   / X MK X /
 *  / X MK X /
 * (________(                @author m.c.kunkel
 *  `------'
*/
package domain;

import java.io.Serializable;

import org.jlab.clas.physics.LorentzVector;
import org.jlab.clas.physics.Particle;
import org.jlab.clas.physics.Vector3;

public class LundParticle implements Serializable {
	private static final long serialVersionUID = 1L;
	private int index;
	private int charge;
	private int type;
	private int pid;
	private int parentIndex;
	private int daughterIndex;
	private double px;
	private double py;
	private double pz;
	private double energy;
	private double mass;
	private double vx;
	private double vy;
	private double vz;
	private LorentzVector partVector;

	public LundParticle(int index, int charge, int type, int pid, int parentIndex, int daughterIndex, double px, double py, double pz,
	        double energy, double mass, double vx, double vy, double vz) {
		this.index = index;
		this.charge = charge;
		this.type = type;
		this.pid = pid;
		this.parentIndex = parentIndex;
		this.daughterIndex = daughterIndex;
		this.px = px;
		this.py = py;
		this.pz = pz;
		this.energy = energy;
		this.mass = mass;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
		this.partVector = new LorentzVector(this.px, this.py, this.pz, this.energy);
	}

	public LundParticle(int index, int charge, int type, int pid, int parentIndex, int daughterIndex, LorentzVector lv, double mass,
	        double vx, double vy, double vz) {
		this.index = index;
		this.charge = charge;
		this.type = type;
		this.pid = pid;
		this.parentIndex = parentIndex;
		this.daughterIndex = daughterIndex;
		this.px = lv.px();
		this.py = lv.py();
		this.pz = lv.pz();
		this.energy = lv.e();
		this.mass = mass;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
		this.partVector = new LorentzVector(this.px, this.py, this.pz, this.energy);

	}

	public LundParticle(int index, int charge, int type, int pid, int parentIndex, int daughterIndex, LorentzVector lv, double mass,
	        Vector3 v3) {
		this.index = index;
		this.charge = charge;
		this.type = type;
		this.pid = pid;
		this.parentIndex = parentIndex;
		this.daughterIndex = daughterIndex;
		this.mass = mass;
		this.vx = v3.x();
		this.vy = v3.y();
		this.vz = v3.z();
		this.px = lv.px();
		this.py = lv.py();
		this.pz = lv.pz();
		this.energy = lv.e();
		this.partVector = new LorentzVector(this.px, this.py, this.pz, this.energy);

	}

	public LundParticle(int index, int type, int parentIndex, int daughterIndex, Particle part, Vector3 v3) {

		this.index = index;
		this.charge = (int) part.charge();
		this.type = type;
		this.pid = part.pid();
		this.parentIndex = parentIndex;
		this.daughterIndex = daughterIndex;
		this.mass = part.mass();
		this.vx = part.vx();
		this.vy = part.vy();
		this.vz = part.vz();
		this.px = part.px();
		this.py = part.py();
		this.pz = part.pz();
		this.energy = part.e();
		this.partVector = new LorentzVector(this.px, this.py, this.pz, this.energy);

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getParentIndex() {
		return parentIndex;
	}

	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}

	public int getDaughterIndex() {
		return daughterIndex;
	}

	public void setDaughterIndex(int daughterIndex) {
		this.daughterIndex = daughterIndex;
	}

	public double getPx() {
		return px;
	}

	public void setPx(double px) {
		this.px = px;
	}

	public double getPy() {
		return py;
	}

	public void setPy(double py) {
		this.py = py;
	}

	public double getPz() {
		return pz;
	}

	public void setPz(double pz) {
		this.pz = pz;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getVz() {
		return vz;
	}

	public void setVz(double vz) {
		this.vz = vz;
	}

	public LundParticle rotateX(double angle) {
		this.vector().rotateX(angle);
		LundParticle tmp = new LundParticle(this.index, this.charge, this.type, this.pid, this.parentIndex, this.daughterIndex,
		        this.vector(), this.mass, this.vx, this.vy, this.vz);
		return tmp;
	}

	public LundParticle rotateY(double angle) {
		this.vector().rotateY(angle);
		LundParticle tmp = new LundParticle(this.index, this.charge, this.type, this.pid, this.parentIndex, this.daughterIndex,
		        this.vector(), this.mass, this.vx, this.vy, this.vz);
		return tmp;
	}

	public LundParticle rotateZ(double angle) {
		this.vector().rotateZ(angle);
		LundParticle tmp = new LundParticle(this.index, this.charge, this.type, this.pid, this.parentIndex, this.daughterIndex,
		        this.vector(), this.mass, this.vx, this.vy, this.vz);
		return tmp;
	}

	public LorentzVector vector() {
		return partVector;
	}

	public double getPhi() {
		return this.vector().phi();
	}

	public double getTheta() {
		return this.vector().theta();
	}

	public double getMag() {
		return this.vector().p();
	}

	@Override
	public String toString() {
		return index + " " + charge + " " + type + " " + pid + " " + parentIndex + " " + daughterIndex + " " + px + " " + py + " " + pz
		        + " " + energy + " " + mass + " " + vx + " " + vy + " " + vz;
	}

}
