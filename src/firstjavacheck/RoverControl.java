package firstjavacheck;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoverControl {

		// TODO Auto-generated method stub

		static class State {
			int _x, _y;  	// Rover position
			int _theta;		// The angle of the direction the rover is heading in units of PI/2 (0 points east)
			
			// Copy constructor required for deep copies
			State (State state) {
				_x = state._x;
				_y = state._y;
				_theta = state._theta;
			}
			
			// Initialise rover from a space separated string like "1 2 N"
			State(String description) {
				String[] parts = description.split(" ");
				_x = Integer.parseInt(parts[0]);
				_y = Integer.parseInt(parts[1]);
				_theta = _codeAngleMap.get(parts[2].charAt(0));
			}
			
			// Convert state back to a space separated string
			String asString() {
				return "" + _x + " " + _y + " " + _angleCodeMap.get(_theta);
			}
			
			// Apply a transform to a rover state (see definition of Xform)
			//  while keeping _theta within the range 0..3
			void xform(Xform m) {
				_theta = (_theta + m._dtheta) % 4; 
				if (_theta < 0)
					_theta += 4;
				_x += Math.round(Math.cos((double)_theta * Math.PI/2.0) * (double)m._distance);
				_y += Math.round(Math.sin((double)_theta * Math.PI/2.0) * (double)m._distance);
			}
		};
		
		// Class that describes a state transform. 
		static class Xform {
			int _distance;  	// Distance to move
			int _dtheta;		// Angle to rotate counter-clockwise in units of PI/2
			Xform(int distance, int dtheta) {
				_distance = distance;
				_dtheta = dtheta;
			}
		};
		
		/***********************************************************************
		 * Some mappings between the input/output encodings and the internal state 
		 * and state transform representations described above.
		 */
		static final Map<Character, Integer> _codeAngleMap = new HashMap<Character, Integer>() {{
		    put('E', 0);
		    put('N', 1);
		    put('W', 2);
		    put('S', 3);
		}};
		static final Map<Integer, Character> _angleCodeMap = new HashMap<Integer, Character>() {{
			for (Character code: _codeAngleMap.keySet()) 
				put(_codeAngleMap.get(code), code);
		}};
		static final Map<Character, Xform> _codeXformMap = new HashMap<Character, Xform>() {{
		    put('M', new Xform(1, 0));
		    put('L', new Xform(0, 1));
		    put('R', new Xform(0, -1));
		}};
		
		/***********************************************************************
		 * The following state and functions describe a single rover. 
		 */
		State _state;
		
		RoverControl(String description) {
			_state = new State(description);
		}

		/* Process a single movement code.	 
		 * Move the rover if the movement code is valid
		 * @return true for valid, false for invalid moves.
		 */
		boolean performInstruction(char code) {
			State newState = new State(_state);
			newState.xform(_codeXformMap.get(code));
			boolean valid = isValidState(newState);
			if (valid)
				_state = newState;
			return valid;
		}
		
		/* Process a list of movement codes and move the rover accordingly.
		 * Stop when on the last valid  code is reached. 
		 * If the initial state is invalid then do nothing. This is the only case
		 *  that leaves the rover in an invalid state.
		 */
		void processInstructionList(String instructionList) {
			if (isValidState(_state)) {
				CharacterIterator it = new StringCharacterIterator(instructionList);
				for (char code=it.first(); code != CharacterIterator.DONE; code=it.next()) {
					if (!performInstruction(code))
						break;
			    }
			}
		}
		
		// @return state of Rover e.g. "1 2 N"
		String getState() {
			return _state.asString();
		}
		
		/***********************************************************************
		 * The following variables and methods describe the rover's environment which
		 * comprises a plateau boundary and previous rovers.
		 */
		static public class Boundary  { 
			private int _x0 = 0, _y0 = 0; 	// Coordinates of bottom left of boundary 
			private int _x1 = 0, _y1 = 0;  	// Coordinates of top right of boundary 
			public  Boundary(String description) {
				String[] parts = description.split(" ");
				_x1 = Integer.parseInt(parts[0]);
				_y1 = Integer.parseInt(parts[1]);
			}
			public boolean contains(int x, int y) {
				return(_x0 <= x && x <= _x1 && 
					   _y0 <= y && y <= _y1);
			}
		};
		
		static Boundary _boundary;
		static List<RoverControl> _completedRovers = new ArrayList<RoverControl>();
		
		static boolean isValidState(State state) {
			boolean valid = true;
			if (!_boundary.contains(state._x, state._y))
				valid = false;
			for (RoverControl rover: _completedRovers) {
				if (rover._state._x == state._x && rover._state._y == state._y) {
					valid = false;
					break;
				}
			}
			return valid;
		}
		
		/***********************************************************************
		 * Process a stream of commands and respond with a stream of status strings.
		 * The input and output formats are specified in the INPUT and OUTPUT section
		 * of the comments at the start of this source file	
		 */
		static void processCommandStream(BufferedReader input, BufferedWriter output) throws IOException {
			String line;
			if ((line = input.readLine()) != null) 
				_boundary = new Boundary(line);
			while (line != null) {
				if ((line = input.readLine()) != null) {
					RoverControl rover = new RoverControl(line);
					if ((line = input.readLine()) != null) {
						rover.processInstructionList(line);
					}
					_completedRovers.add(rover);
					output.write(rover.getState() + '\n') ;
					output.flush();
				}
			}
		}
		
		/***********************************************************************
		 * Program entry point
		 * 	Processes a stream of rover commands as described in processCommandStream()
		 * 	Reads input from stdin and writes output to stdout
		 */
		public static void main(String[] args) throws IOException  {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));   
		    BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out)); 
		   	try {
				processCommandStream(input, output);
			} finally {
				input.close();
				output.close();
			}
		}

	}
