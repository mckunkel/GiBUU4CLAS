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

public interface OpenCloseLundFile {

	public void openLundFile();

	public void openLundFile(String outputLundName);

	public void closeLundFile();

	public void writeEvent(LundHeader lHeader);

	public void writeEvent(LundParticle lPart);

	public void writeFlush();

}
