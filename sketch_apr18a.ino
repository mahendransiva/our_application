// void setup() {
//   // put your setup code here, to run once:

// }

// void loop() {
//   // put your main code here, to run repeatedly:

// }
#include <DHT.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <SoftwareSerial.h>
#define DHTPIN 2 // Temperature sensor pin
#define DHTTYPE DHT11
DHT dht(DHTPIN, DHTTYPE);
#define SOIL_MOISTURE_PIN A1
#define GAS_SENSOR_PIN A0
#define LDR_PIN A2
#define PIR_PIN 3
#define TRIG_PIN 9
#define ECHO_PIN 10
#define RELAY_PIN 8
LiquidCrystal_I2C lcd(0x27, 16, 2); // Adjust if needed
SoftwareSerial gsm(11, 10); // GSM module TX to 11, RX to 10
void setup() {
 Serial.begin(9600);
 gsm.begin(9600);
 dht.begin();
 lcd.begin();
 lcd.backlight();
 pinMode(PIR_PIN, INPUT);
 pinMode(TRIG_PIN, OUTPUT);
 pinMode(ECHO_PIN, INPUT);
 pinMode(RELAY_PIN, OUTPUT);
}
void loop() {
 float temperature = dht.readTemperature();
 float humidity = dht.readHumidity();
 int soilMoistureValue = analogRead(SOIL_MOISTURE_PIN);
 int gasLevel = analogRead(GAS_SENSOR_PIN);
 int ldrValue = analogRead(LDR_PIN);
 int pirState = digitalRead(PIR_PIN);
 lcd.clear();
 lcd.setCursor(0, 0);
 lcd.print("Temp: ");
 lcd.print(temperature);
 lcd.print("C");
 lcd.setCursor(0, 1);
 lcd.print("Soil: ");
 lcd.print(soilMoistureValue);
 // Soil moisture management
 if (soilMoistureValue < 400) {
 digitalWrite(RELAY_PIN, HIGH);
 delay(1000);
 lcd.setCursor(8, 1);
 lcd.print("Pump ON");
 sendSMS("Soil is dry. Pump activated.");
 } else {
 digitalWrite(RELAY_PIN, LOW);
 lcd.setCursor(8, 1);
 lcd.print("Pump OFF");
 }
 // Read Ultrasonic Sensor for distance
 long duration, distance;
 digitalWrite(TRIG_PIN, LOW);
 delayMicroseconds(2);
 digitalWrite(TRIG_PIN, HIGH);
 delayMicroseconds(10);
 digitalWrite(TRIG_PIN, LOW);
 duration = pulseIn(ECHO_PIN, HIGH);
 distance = duration * 0.034 / 2;

 // Light management
 if (ldrValue > 500) {
 sendSMS("Field lighting required.");
 }
 // PIR detection
 if (pirState == HIGH) {
 sendSMS("Intruder detected in field.");
 }
 // Check for gas level
 if (gasLevel > 300) {
 sendSMS("High gas levels detected.");
 }
 delay(2000);
}
void sendSMS(String message) {
 gsm.print("AT+CMGF=1\r"); // Set SMS mode to text
 delay(1000);
 gsm.print("AT+CMGS=\"+91XXXXXXXXXX\"\r"); // Replace Xs with target number
 delay(1000);
 gsm.print(message);
 delay(1000);
 gsm.write(26); // ASCII code for Ctrl+Z to send SMS
 delay(1000);
}