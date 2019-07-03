export class SpectacolData {
  id: number;
  pret: number;
  data: number;
  spectacolId: number;

  constructor(id, pret, data, spectacolId) {
    this.id = id;
    this.pret = pret;
    this.data = data;
    this.spectacolId = spectacolId;
  }
}
