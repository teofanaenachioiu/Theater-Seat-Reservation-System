export class Rezervare {
  id: number;
  denumire: string;
  descriere: string;
  data: number;
  pret: number;
  pozitieX: number;
  pozitieY: number;
  nrScaun: number;



  constructor(den, desc, dataSpec, pr, pozX, pozY, nr) {
    this.denumire = den;
    this.descriere = desc;
    this.data = dataSpec;
    this.pret = pr;
    this.pozitieX = pozX;
    this.pozitieY = pozY;
    this.nrScaun = nr;
  }

}
