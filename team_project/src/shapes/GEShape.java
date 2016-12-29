package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import constants.GEConstants.EAnchorTypes;
import utils.GEAnchorList;

public abstract class GEShape {
	protected Shape myShape;
	protected Point startP;
	protected boolean selected;
	protected GEAnchorList anchorList;
	protected EAnchorTypes selectedAnchor;
	protected Color lineColor, fillColor;
	protected AffineTransform affineTransform; 
	
	// GEShape�� ������
	public GEShape(Shape myShape){
		this.myShape = myShape;
		this.selected = false;
		this.anchorList = null;
		affineTransform = new AffineTransform(); 
	}

		
	// lineColor�� ����
	public void setLineColor(Color lineColor){   
		this.lineColor = lineColor;  
	}  

	// fillColor�� ����
	public void setFillColor(Color fillColor){
		this.fillColor = fillColor;
	}
	
	// AnchorList�� ��ȯ
	public GEAnchorList getAnchorList(){   
		return anchorList;  
	}  
	
	// ���õ� ��Ŀ�� ��ȯ
	public EAnchorTypes getSelectedAnchor(){     
		return selectedAnchor;         
	}  
	
	// ���É����� ���õ��� ��ȯ
	public boolean isSelected(){   
		return selected;  
	} 
	
	// ��Ŀ�� �÷����� �簢��Ʋ�� ��ȯ
	public Rectangle getBounds(){   
		return myShape.getBounds();  
	}  
	
	// �ش� ������ �׸�
	public void draw(Graphics2D g2D){ 
		if(fillColor != null){
			g2D.setColor(fillColor);
			g2D.fill(myShape);
		}
		if(lineColor != null){
			g2D.setColor(lineColor);
			g2D.draw(myShape);
		}
		
		if(selected == true){
			anchorList.setPosition(myShape.getBounds());
			anchorList.draw(g2D);
		}
	}

	// ���õǾ��������� ���É�ٸ� GEAnchorList�� �����ؼ� ��Ŀ�� �׷��ش�.
	// ���õ��� �ʾҴٸ� ��Ŀ�� �׸��� �ʴ´�.
	public void setSelected (boolean selected){
		this.selected = selected;
		if(selected == true){
			anchorList = new GEAnchorList();
			anchorList.setPosition(myShape.getBounds());
		}else{
			anchorList = null;
		}
	}

	// �ش絵�������� ���콺�� ��Ŀ���� �ִ��� �ƴ����� �Ǵ�
	public boolean onShape(Point p){ 
		if (anchorList!=null){
			selectedAnchor = anchorList.onAnchors(p);    
			if (selectedAnchor != EAnchorTypes.NONE )   
				return true;  
		} 
		return myShape.intersects(new Rectangle(p.x, p.y, 2, 2)); 
	}  
	
	// �ش� �������� ���콺�� � ��Ŀ�� �����ߴ����� ��ȯ
	public EAnchorTypes onAnchor(Point p){  
		this.selectedAnchor = anchorList.onAnchors(p);  
		return selectedAnchor; 
	}  
	
	// ����� �⺻�࿬���� ���� �ش� ������ ũ�⸦ ������
	public void resizeCoordinate(Point2D resizeFactor){   
		affineTransform.setToScale(resizeFactor.getX(), resizeFactor.getY());  
		myShape = affineTransform.createTransformedShape(myShape); 
	} 
	
	// ����� �⺻�࿬���� ���� �ش� ������ �̵���Ŵ
	public void moveCoordinate(Point moveP){      
		affineTransform.setToTranslation(moveP.x, moveP.y);
		myShape = affineTransform.createTransformedShape(myShape);   
	} 
	
	// ����� �⺻�࿬���� ���� �ش� ������ ũ�⸦ ������
	public void moveReverse(Point resizeAnchor){  
		affineTransform.setToTranslation(-resizeAnchor.x, -resizeAnchor.y); 
		myShape = affineTransform.createTransformedShape(myShape);
	} 
	
	// ����� �⺻�࿬���� ���� �ش� ������ ũ�⸦ ������
	public void move(Point resizeAnchor){   
		affineTransform.setToTranslation(resizeAnchor.x, resizeAnchor.y); 
		myShape = affineTransform.createTransformedShape(myShape);
	}
	
	//����� �⺻ ������ ���� �ش� ������ ȸ�������� �ٲ�
	public void rotaterCoordinate(double theta, Point2D rotaterAnchor){
		affineTransform.setToRotation(theta, rotaterAnchor.getX(), rotaterAnchor.getY() );
		myShape = affineTransform.createTransformedShape(myShape);
	}
	
	//������ �Է°��� ���� ������ ȸ����Ŵ
	public void rotaterCoordinate_for_userInput(double theta, Point2D rotaterAnchor){
		affineTransform.setToRotation(Math.toRadians(theta), rotaterAnchor.getX(), rotaterAnchor.getY() );
		myShape = affineTransform.createTransformedShape(myShape);
	}
	
	
	abstract public void initDraw(Point startP);
	abstract public void setCoordinate(Point currentP);
	abstract public GEShape clone();

}