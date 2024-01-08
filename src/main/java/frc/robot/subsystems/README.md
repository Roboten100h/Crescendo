# Contrôleurs moteurs 

## (Robot avec convoyeur)
SparkMax -> CAN 1,2,3,4 = Drivetrain            | 4 CIM
SparkMax -> CAN 5       = Outtake Feeder        | Bag
SparkMax -> CAN 6,7     = Outtake FlyWheel      | Neo

TalonSR -> PWM 0,1  = Climber                   | Bag
TalonSR -> PWM 2    = Conveyer                  | Bag  

## (Robot simple)
TalonSR x4  ->       = Drivetrain               | CIM
VictorSPX   ->       = Lanceur                  | CIM
VictorSPX   ->       = Convoyeur                | Bag


