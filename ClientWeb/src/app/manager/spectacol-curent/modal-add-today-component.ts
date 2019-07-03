import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {el} from '@angular/platform-browser/testing/src/browser_util';

@Component({
  selector: 'ngbd-modal-add-today',
  template: `
    <div class="modal-header">
      <h4 class="modal-title" id="modal-title">Set Today Show</h4>
      <button type="button" class="close" aria-describedby="modal-title" (click)="modal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div *ngIf="this.canAdd">
      <div class="modal-body">
        <div class="form-group">
          <label for="formGroupExampleInput">Price</label>
          <input [(ngModel)]="price" type="text" class="form-control" id="formGroupExampleInput" placeholder="show price...">
          <label>Time</label>
          <ngb-timepicker [(ngModel)]="selectedTime"></ngb-timepicker>
        </div>
        <strong>{{error}}</strong>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-secondary" (click)="modal.dismiss('cancel click')">Cancel</button>
        <button type="button" class="btn btn-primary" (click)="saveData()">Ok</button>
      </div>
    </div>
    <div *ngIf="!this.canAdd">
      <div class="modal-body">
        <label>Next Show in...</label>
        <countdown [config]="{stopTime: timpRamas}">$!h!:$!m!:$!s!</countdown>
      </div>
    </div>
  `
})

export class NgbdModalAddTodayShowContent {
  @Input() error = '';
  @Input() price;
  @Input() canAdd;
  @Input() timpRamas;
  @Output() passEntry: EventEmitter<any> = new EventEmitter();
  selectedTime: any;

  constructor(public modal: NgbActiveModal) {
  }

  saveData() {
    const hour1 = this.selectedTime.hour;
    const minutes1 = this.selectedTime.minute;
    if (this.price !== undefined && this.price !== ''  && hour1 !== undefined || minutes1 === undefined) {
      this.passEntry.emit({price: this.price, hour: hour1, minutes: minutes1});
      this.modal.close();
    } else {
      this.error = 'All fields are required';
    }
  }
}
